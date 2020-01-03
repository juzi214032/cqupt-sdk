package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.alibaba.fastjson.JSON;
import com.juzi.cqupt.sdk.common.enums.CASService;
import com.juzi.cqupt.sdk.common.exception.CASLoginFailedException;
import com.juzi.cqupt.sdk.common.util.CASLoginUtil;
import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxLoginResult;
import com.juzi.cqupt.sdk.jwzx.config.JwzxConfig;
import com.juzi.cqupt.sdk.jwzx.constant.JwzxConstants;
import com.juzi.cqupt.sdk.jwzx.exception.JwzxLoginFailedException;
import com.juzi.cqupt.sdk.jwzx.util.AuthCodeUtil;
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
 * @author Juzi
 * @since 2019/8/1 19:56
 * Blog https://juzibiji.top
 */
@Slf4j
public class JwzxServiceImpl implements JwzxService {

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
    public String login(String username, String password) {
        return this.login(username, password, false);
    }

    @Override
    public String login(String username, String password, boolean forceUpdate) {

        if (username.length() == 7) {
            try {
                return this.loginByCAS(username, password);
            } catch (CASLoginFailedException e) {
                e.printStackTrace();
            }
        }

        if (!forceUpdate) {
            // 检测缓存中是否有cookie
            String oldCookie = cookieCache.get(username);
            if (!StringUtils.isBlank(oldCookie)) {
                log.debug("从缓存中获取到cookie");
                return oldCookie;
            }
        }

        int maxRetryTime = jwzxConfig.getMaxRetryTimes();
        int retryTime = 0;

        // 获取验证码和cookie
        Connection.Response response = null;
        do {
            try {
                response = Jsoup
                        .connect(jwzxConfig.getDomain() + AUTH_CODE_IMG_URL)
                        .ignoreContentType(true)
                        .execute();
            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", jwzxConfig.getDomain() + AUTH_CODE_IMG_URL, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1 && response == null);

        if (response == null) {
            log.warn("重试达到最大次数【{}】", maxRetryTime);
            throw new RuntimeException("访问教务在线失败，请检查网络是否连通！");
        }
        retryTime = 0;

        // sessionId
        String cookie = response.cookie(JwzxConstants.COOKIE_NAME);
        BufferedInputStream bufferedInputStream = response.bodyStream();
        String authCode = AuthCodeUtil.jwzx(bufferedInputStream);

        // 构造请求参数
        Map<String, String> params = new HashMap<>(3);
        params.put(JwzxConstants.LoginParam.USERNAME, username);
        params.put(JwzxConstants.LoginParam.PASSWORD, password);
        params.put(JwzxConstants.LoginParam.AUTH_CODE, authCode);

        // 发起请求，解析JSON
        String json = null;

        do {
            try {
                json = Jsoup
                        .connect(jwzxConfig.getDomain() + LOGIN_URL)
                        .header("Referer", jwzxConfig.getDomain())
                        .cookie(JwzxConstants.COOKIE_NAME, cookie)
                        .data(params)
                        .post()
                        .text();
            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", jwzxConfig.getDomain() + LOGIN_URL, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1 && json == null);

        if (json == null) {
            log.warn("重试达到最大次数【{}】", maxRetryTime);
            throw new RuntimeException("访问教务在线失败，请检查网络是否连通！");
        }

        JwzxLoginResult result = JSON.parseObject(json, JwzxLoginResult.class);

        if (JwzxConstants.LoginResultCode.LOGIN_SUCCESS.equals(result.getCode())) {
            // 登录成功，保存cookie
            cookieCache.put(username, cookie);
            return cookie;
        } else {
            // 登录失败，抛出异常
            throw new JwzxLoginFailedException();
        }
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
    public String loginByCAS(String username, String password) throws CASLoginFailedException {
        Map<String, String> cookies = CASLoginUtil.login(username, password, CASService.JWZX);
        return cookies.get(JwzxConstants.COOKIE_NAME);
    }
}
