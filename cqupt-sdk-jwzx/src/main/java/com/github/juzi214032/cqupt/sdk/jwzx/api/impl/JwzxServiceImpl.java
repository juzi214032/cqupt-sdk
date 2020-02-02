package com.github.juzi214032.cqupt.sdk.jwzx.api.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.github.juzi214032.cqupt.sdk.common.enums.CASService;
import com.github.juzi214032.cqupt.sdk.common.exception.CASLoginFailedException;
import com.github.juzi214032.cqupt.sdk.common.util.CASLoginUtil;
import com.github.juzi214032.cqupt.sdk.common.util.HttpUtil;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxLoginResult;
import com.github.juzi214032.cqupt.sdk.jwzx.config.JwzxConfig;
import com.github.juzi214032.cqupt.sdk.jwzx.constant.JwzxConstants;
import com.github.juzi214032.cqupt.sdk.jwzx.exception.JwzxLoginFailedException;
import com.github.juzi214032.cqupt.sdk.jwzx.util.AuthCodeUtil;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 教务在线公用逻辑实现代码
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 19:56
 */
@Slf4j
public class JwzxServiceImpl implements JwzxService {

    /**
     * 配置类
     */
    private JwzxConfig jwzxConfig;

    /**
     * 存放cookie的cache，半小时失效
     */
    private Cache<String, String> cookieCache;

    public JwzxServiceImpl(JwzxConfig jwzxConfig) {
        this.jwzxConfig = jwzxConfig;
        URL myUrl = getClass().getResource("/jwzx-ehcache-config.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        this.cookieCache = cacheManager.getCache("jwzxCookieCache", String.class, String.class);
    }

    @Override
    public JwzxConfig getConfig() {
        return jwzxConfig;
    }

    @Override
    public Document get(String uri) {
        return get(uri, null, null, null);
    }

    @Override
    public Document get(String uri, String username, String password) {
        return get(uri, username, password, null);
    }

    @Override
    public Document get(String uri, Map<String, String> data) {
        return this.get(uri, null, null, data);
    }

    @Override
    public Document get(String uri, String username, String password, Map<String, String> data) {
        if (data == null) {
            data = Collections.emptyMap();
        }

        String url = jwzxConfig.getDomain() + uri;
        int maxRetryTime = jwzxConfig.getMaxRetryTimes();
        int retryTime = 0;

        String cookie = "";
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            cookie = this.login(username, password);
        }

        do {
            try {
                Connection.Response execute = Jsoup
                        .connect(url)
                        .cookie(JwzxConstants.COOKIE_NAME, cookie)
                        .ignoreContentType(true)
                        .data(data)
                        .method(Connection.Method.GET)
                        .followRedirects(false)
                        .execute();

                // 如果cookie失效，会发生302跳转到login.php
                String location = execute.header("location");

                if (!StringUtils.isBlank(location)) {
                    log.info("用户{}的cookie失效，尝试访问url：{}，已尝试{}次，将重新获取cookie", username, url, retryTime + 1);
                    cookieCache.remove(username);
                } else {
                    return execute.parse();
                }

            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", url, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1);

        log.warn("重试达到最大次数【{}】", maxRetryTime);
        throw new RuntimeException("访问教务在线失败，请检查网络是否连通！");
    }

    @Override
    public Document post(String uri) {
        return this.post(uri, null, null, null);
    }

    @Override
    public Document post(String uri, String username, String password) {
        return this.post(uri, username, password, null);
    }

    @Override
    public Document post(String uri, Map<String, String> data) {
        return this.post(uri, null, null, data);
    }

    @Override
    public Document post(String uri, String username, String password, Map<String, String> data) {
        if (data == null) {
            data = Collections.emptyMap();
        }

        String url = jwzxConfig.getDomain() + uri;
        int maxRetryTime = 5;
        int retryTime = 0;

        String cookie = "";
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            cookie = this.login(username, password);
        }

        do {
            try {
                Connection.Response execute = Jsoup
                        .connect(url)
                        .cookie(JwzxConstants.COOKIE_NAME, cookie)
                        .data(data)
                        .method(Connection.Method.POST)
                        .followRedirects(false)
                        .execute();

                // 如果cookie失效，会发生302跳转到login.php
                String location = execute.header("location");

                if (!StringUtils.isBlank(location)) {
                    log.info("用户{}的cookie失效，尝试访问url：{}，已尝试{}次，将重新获取cookie", username, url, retryTime + 1);
                    if (username != null) {
                        cookieCache.remove(username);
                    }
                } else {
                    return execute.parse();
                }

            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", url, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1);

        log.warn("重试达到最大次数【{}】", maxRetryTime);
        throw new RuntimeException("访问教务在线失败，请检查网络是否连通！");
    }

    @Override
    public Connection.Response excute(String uri, Connection.Method method) {
        Connection.Response execute = null;
        try {
            execute = Jsoup
                    .connect(jwzxConfig.getDomain() + uri)
                    .ignoreContentType(true)
                    .method(method)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute;
    }

    @Override
    public Connection.Response excute(Connection.Method method, String url) {
        return this.excute(method,url,null,null,null,null);
    }

    @Override
    public Connection.Response excute(Connection.Method method, String url, Map<String, String> queryData, Map<String, String> headerData, Map<String, String> cookieData, String bodyData) {
        // 请求url
        url=this.jwzxConfig.getDomain()+url;
        // 超时重试次数
        int maxRetryTimes = this.jwzxConfig.getMaxRetryTimes();
        // http请求超时时间
        int timeout = this.jwzxConfig.getTimeout();

        for (int i = 1; i <= maxRetryTimes; i++) {
            try {
                return HttpUtil.excute(method, url, queryData, headerData, cookieData, bodyData, timeout);
            } catch (IOException e) {
                if (i == maxRetryTimes) {
                    log.warn("请求OA服务失败，访问url[{}]", url, e);
                    throw new RuntimeException("请求教务在线失败",e);
                } else {
                    log.info("请求OA服务失败，访问url[{}]", url);
                }
            }
        }

        return null;
    }

    @Override
    public String login(String username, String password) {
        return this.login(username, password, false);
    }

    @Override
    public String login(String username, String password, boolean forceUpdate) {
        if (username.length() == 7) {
            try {
                return this.loginByCAS(username, password);
            } catch (CASLoginFailedException e) {
                log.error("用户[{}]，登录教务在线失败", username, e);
                throw new JwzxLoginFailedException(e);
            }
        } else {
            try {
                return this.loginBySystem(username, password);
            } catch (IOException | JwzxLoginFailedException e) {
                log.error("用户[{}]，登录教务在线失败", username, e);
                throw new JwzxLoginFailedException(e);
            }
        }
    }

    @Override
    public String loginByCAS(String username, String password) throws CASLoginFailedException {
        log.debug("用户[{}]，开始使用CAS登录教务在线", username);

        int maxRetryTime = jwzxConfig.getMaxRetryTimes();
        String cacheKey = DigestUtil.md5Hex(username + password);

        // 查找缓存
        String cacheCookie = cookieCache.get(cacheKey);
        if (cacheCookie != null) {
            log.debug("用户[{}]，使用CAS登录教务在线成功", username);
            log.debug("用户[{}]，结束使用CAS登录教务在线", username);
            return cacheCookie;
        }

        // 发起请求
        Map<String, String> cookies = null;
        for (int i = 1; i <= maxRetryTime; i++) {
            try {
                cookies = CASLoginUtil.login(username, password, CASService.JWZX);
            } catch (CASLoginFailedException e) {
                if (i == maxRetryTime) {
                    log.warn("用户[{}]，使用CAS登录教务在线失败", username, e);
                    throw e;
                } else {
                    log.info("用户[{}]，使用CAS登录教务在线失败", username);
                }
            }
        }

        String cookie = cookies.get(JwzxConstants.COOKIE_NAME);
        cookieCache.put(cacheKey, cookie);
        log.debug("用户[{}]，使用CAS登录教务在线成功，cookie已存入缓存", username);
        log.debug("用户[{}]，结束使用CAS登录教务在线", username);
        return cookie;
    }

    @Override
    public String loginBySystem(String username, String password) throws IOException, JwzxLoginFailedException {
        log.debug("用户[{}]，开始使用教务在线内置系统登录", username);

        int maxRetryTime = jwzxConfig.getMaxRetryTimes();
        String cacheKey = DigestUtil.md5Hex(username + password);

        // 查找缓存
        String cacheCookie = cookieCache.get(cacheKey);
        if (cacheCookie != null) {
            log.debug("用户[{}]，从缓存获取到cookie", username);
            log.debug("用户[{}]，结束使用教务在线内置系统登录", username);
            return cacheCookie;
        }

        // 获取登录验证码
        Connection.Response response = null;
        for (int i = 1; i <= maxRetryTime; i++) {
            try {
                response = HttpUtil.excute(Connection.Method.GET, jwzxConfig.getDomain() + AUTH_CODE_IMG_URL, jwzxConfig.getTimeout());
                break;
            } catch (IOException e) {
                if (i == maxRetryTime) {
                    log.warn("用户[{}]，使用教务在线内置系统登陆时，获取验证码失败", username, e);
                    throw e;
                } else {
                    log.info("用户[{}]，使用教务在线内置系统登陆时，获取验证码失败", username);
                }
            }
        }

        // 获取sessionId
        String cookie = response.cookie(JwzxConstants.COOKIE_NAME);
        // 识别验证码
        BufferedInputStream bufferedInputStream = response.bodyStream();
        String authCode = AuthCodeUtil.jwzx(bufferedInputStream);

        // 构造body参数
        Map<String, String> bodyData = new HashMap<>(3);
        bodyData.put(JwzxConstants.LoginParam.USERNAME, username);
        bodyData.put(JwzxConstants.LoginParam.PASSWORD, password);
        bodyData.put(JwzxConstants.LoginParam.AUTH_CODE, authCode);

        // 构造cookie参数
        Map<String, String> cookieData = new HashMap<>(1);
        cookieData.put(JwzxConstants.COOKIE_NAME, cookie);

        // 发起请求，解析JSON
        String json = null;
        for (int i = 1; i <= maxRetryTime; i++) {
            try {
                json = HttpUtil.post(jwzxConfig.getDomain() + LOGIN_URL, null, null, cookieData, HttpUtil.convertMapToUrlencoded(bodyData), this.jwzxConfig.getTimeout()).text();
                break;
            } catch (IOException e) {
                if (i == maxRetryTime) {
                    log.warn("用户[{}]，使用教务在线内置系统登陆时，登录失败", username, e);
                    throw e;
                } else {
                    log.info("用户[{}]，使用教务在线内置系统登陆时，登录失败", username);
                }
            }
        }
        JwzxLoginResult result = JSON.parseObject(json, JwzxLoginResult.class);

        // 根据教务在线返回信息，判断是否登录成功
        if (JwzxConstants.LoginResultCode.LOGIN_SUCCESS.equals(result.getCode())) {
            // 登录成功，保存cookie
            cookieCache.put(cacheKey, cookie);
            log.debug("用户[{}]，登录成功，cookie已存入缓存", username);
            log.debug("用户[{}]，结束使用教务在线内置系统登录", username);
            return cookie;
        } else {
            // 登录失败，抛出异常
            log.info("用户[{}]，使用教务在线内置系统登陆时，登录失败", username);
            throw new JwzxLoginFailedException();
        }
    }


}
