package com.juzi.library.api.impl;

import com.juzi.cqupt.sdk.common.component.SdkHttpClient;
import com.juzi.library.api.LibService;
import com.juzi.library.config.LibConfig;
import com.juzi.library.constant.LibConstants;
import com.juzi.library.exception.LibAccountUncheckException;
import com.juzi.library.exception.LibException;
import com.juzi.library.util.LibAuthCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Juzi
 * @since 2019/8/16 14:41
 * Blog https://juzibiji.top
 */
@Slf4j
public class LibServiceImpl implements LibService {

    /**
     * 验证姓名页面的URL
     */
    private static final String CHECK_NAME_URL = "redr_con.php";

    /**
     * 登录结果页面的URL
     * 登录失败才会跳转到登录结果页
     */
    private static final String LOGIN_RESULT_URL = "/reader/redr_con_result.php";

    /**
     * 个人信息页面的URL
     * 登录成功会跳转到此页
     */
    private static final String PERSONAL_INFO_URL = "redr_info.php";

    private LibConfig libConfig;

    /**
     * http请求工具
     */
    private SdkHttpClient sdkHttpClient;

    /**
     * 存放cookie的cache，半小时失效
     */
    private Cache<String, String> cookieCache;

    /**
     * 构造函数
     *
     * @param libConfig 实例对象
     */
    public LibServiceImpl(LibConfig libConfig) {
        this.libConfig = libConfig;
        sdkHttpClient = new SdkHttpClient();
        sdkHttpClient
                .setBaseUrl(this.libConfig.getDomain())
                .setMaxRetryTimes(5)
                .setMaxTimeOut(3000L);

        // 初始化ehcache容器，用于存储cookie
        URL myUrl = getClass().getResource("/lib-ehcache-config.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        this.cookieCache = cacheManager.getCache("libCookieCache", String.class, String.class);
    }

    /**
     * 构造函数
     *
     * @return 实例对象
     */
    @Override
    public LibConfig getConfig() {
        return libConfig;
    }

    @Override
    public Map<String, String> login(String username, String password) {
        return this.login(username, password, null, false);
    }

    @Override
    public Map<String, String> login(String username, String password, String name, boolean forceUpdate) {
        log.info("开始登录，用户===>{}", username);
        if (!forceUpdate) {
            // 检测缓存中是否有cookie
            String oldCookie = cookieCache.get(username);
            if (!StringUtils.isBlank(oldCookie)) {
                log.debug("获取到cookie=====>{},登录用户=====>{}", oldCookie, username);
                Map<String, String> oldCookies = new HashMap<>(1);
                oldCookies.put(LibConstants.COOKIE_NAME, oldCookie);
                return oldCookies;
            }
        }

        // 获取验证码和cookie
        Connection.Response autoCodeResponse = this.sdkHttpClient
                .excute(Connection.Method.GET, AUTH_CODE_IMG_URL, null, null);

        // sessionId
        String cookie = autoCodeResponse.cookie(LibConstants.COOKIE_NAME);
        BufferedInputStream bufferedInputStream = autoCodeResponse.bodyStream();
        String authCode = LibAuthCodeUtil.jwzx(bufferedInputStream);

        // 构造Cookies
        Map<String, String> cookies = new HashMap<>(1);
        cookies.put(LibConstants.COOKIE_NAME, cookie);

        // 构造请求参数
        Map<String, String> data = new HashMap<>(4);
        data.put("number", username);
        data.put("passwd", password);
        data.put("captcha", authCode);
        data.put("select", "cert_no");
        data.put("returnUrl", "");

        // 请求登录接口
        Connection.Response loginResponse = this.sdkHttpClient
                .excute(Connection.Method.POST, LOGIN_API_URL, cookies, data);

        Document loginDocument = null;
        try {
            loginDocument = loginResponse.parse();
        } catch (IOException e) {
            log.error("Html解析失败，访问URL：{}，访问用户：{}", LOGIN_API_URL, username);
            throw new LibException("解析Html文档错误");
        }

        // 登录后的304重定向url
        String locationUrl = loginResponse.header("Location");

        // 错误提示
        Elements loginTipTableElements = loginDocument.select("form>table");
        Elements loginTipFontElements = loginTipTableElements.size() > 0 ? loginTipTableElements.get(0).getElementsByTag("font") : new Elements();
        String loginTip = loginTipFontElements.size() > 0 ? loginTipFontElements.get(0).ownText() : "";

        // 第一次登录，需要验证姓名
        if (CHECK_NAME_URL.equals(locationUrl)) {

            if (name == null) {
                log.warn("登录失败，用户：{} 第一次登录图书馆网站，未传入姓名做校验", username);
                throw new LibAccountUncheckException();
            }

            // 组装请求参数
            Map<String, String> nameData = new HashMap<>(1);
            nameData.put("name", name);
            //请求名字验证接口
            Connection.Response checkNameResponse = sdkHttpClient.excute(Connection.Method.POST, CHECK_NAME_API_URL, cookies, nameData);

            Document checkNameDocument = null;
            try {
                checkNameDocument = checkNameResponse.parse();
            } catch (IOException e) {
                log.error("Html解析失败，访问URL：{}，访问用户：{}", CHECK_NAME_API_URL, username);
            }

            String currentUrl = checkNameResponse.url().getPath();

            // 错误提示
            Elements checkNameTableElements = checkNameDocument.getElementsByTag("table");
            Elements checkNameTipElements = checkNameTableElements.size() > 0 ? checkNameTableElements.get(0).getElementsByTag("font") : new Elements();
            String checkNameTips = checkNameTipElements.size() > 0 ? checkNameTipElements.get(0).ownText() : "";

            if (LOGIN_RESULT_URL.equals(currentUrl) && !"".equals(checkNameTips)) {
                throw new LibException(loginTip);
            }
        } else if (!"".equals(loginTip)) {
            throw new LibException(loginTip);
        }

        log.info("登录成功，用户===>{}", username);
        this.cookieCache.put(username, cookie);
        return cookies;
    }

    @Override
    public Document get(String uri, String username, String password) {
        Map<String, String> cookies = this.login(username, password);
        return this.sdkHttpClient.get(uri, cookies);
    }
}
