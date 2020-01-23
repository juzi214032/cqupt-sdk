package com.github.juzi214032.cqupt.sdk.volunteer.api.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.alibaba.fastjson.JSON;
import com.github.juzi214032.cqupt.sdk.volunteer.api.VolunteerWebService;
import com.github.juzi214032.cqupt.sdk.volunteer.bean.VolunteerWebLoginResult;
import com.github.juzi214032.cqupt.sdk.volunteer.config.VolunteerWebConfig;
import com.github.juzi214032.cqupt.sdk.volunteer.constant.VolunteerWebConstant;
import com.github.juzi214032.cqupt.sdk.volunteer.exception.VolunteerWebException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/8 21:54
 */
@Slf4j
public class VolunteerWebServiceImpl implements VolunteerWebService {

    private VolunteerWebConfig volunteerWebConfig;

    private static final AsymmetricCrypto RSA = new AsymmetricCrypto("RSA", null, VolunteerWebConstant.PUBLIC_KEY);

    /**
     * 存放cookie的cache，半小时失效
     */
    private Cache<String, String> cookieCache;

    public VolunteerWebServiceImpl(VolunteerWebConfig volunteerWebConfig) {
        this.volunteerWebConfig = volunteerWebConfig;
        URL myUrl = getClass().getResource("/volunteer-web-ehcache-config.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        this.cookieCache = cacheManager.getCache("volunteerCookieCache", String.class, String.class);
    }

    @Override
    public VolunteerWebConfig getConfig() {
        return this.volunteerWebConfig;
    }

    @Override
    public String login(String username, String password) {

        return this.login(username, password, false);
    }

    @Override
    public String login(String username, String password, boolean forceUpdate) {
        if (!forceUpdate) {
            // 检测缓存中是否有cookie
            String oldCookie = cookieCache.get(username);
            if (!StringUtils.isBlank(oldCookie)) {
                log.debug("从缓存中获取到cookie");
                return oldCookie;
            }
        }

        int maxRetryTime = 10;
        int retryTime = 0;

        // 获取cookie
        Connection.Response response = null;
        do {
            try {
                response = Jsoup
                        .connect(volunteerWebConfig.getDomain() + LOGIN_PAGE_URL)
                        .timeout(3000)
                        .execute();
            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", volunteerWebConfig.getDomain() + LOGIN_PAGE_URL, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1 && response == null);

        if (response == null) {
            log.warn("重试达到最大次数【{}】", maxRetryTime);
            throw new RuntimeException("访问中国志愿服务网失败，请检查网络是否连通！");
        }
        retryTime = 0;

        // 获取cookie
        String cookie = response.cookie(VolunteerWebConstant.COOKIE_NAME);

        Document document = null;
        try {
            document = response.parse();
        } catch (IOException e) {
            log.error("解析登录页面出错，登录账号{}", username);
        }
        String seid = document.select("#seid").val();
        String csrfToken = document.getElementsByTag("meta").get(1).attr("content");

        // 密码加密
        try {
            password = Base64.encode(RSA.encrypt(password, KeyType.PublicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 构造请求参数
        Map<String, String> data = new HashMap<>(3);
        data.put("seid", seid);
        data.put("uname", username);
        data.put("upass", password);

        // 发起请求，解析JSON
        String json = null;

        do {
            try {
                json = Jsoup
                        .connect(volunteerWebConfig.getDomain() + LOGIN_API_URL)
                        .timeout(3000)
                        .header("Referer", volunteerWebConfig.getDomain())
                        .cookie(VolunteerWebConstant.COOKIE_NAME, cookie)
                        .data(data)
                        .post()
                        .text();
            } catch (IOException e) {
                log.warn("网络连接失败，尝试访问url：{}，已尝试{}次，错误原因：{}", volunteerWebConfig.getDomain() + LOGIN_API_URL, retryTime + 1, e.getMessage());
            }
        } while (retryTime++ < maxRetryTime - 1 && json == null);

        if (json == null) {
            log.warn("重试达到最大次数【{}】", maxRetryTime);
            throw new RuntimeException("访问中国志愿服务网失败，请检查网络是否连通！");
        }

        VolunteerWebLoginResult volunteerWebLoginResult = JSON.parseObject(json, VolunteerWebLoginResult.class);

        if (!"0".equals(volunteerWebLoginResult.getCode())) {
            throw new VolunteerWebException("登录失败");
        }

        // 登录成功，保存cookie
        cookieCache.put(username, cookie);
        return cookie;
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

        String url = volunteerWebConfig.getDomain() + uri;
        int maxRetryTime = 10;
        int retryTime = 0;

        String cookie = "";
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            cookie = this.login(username, password);
        }

        do {
            try {
                Connection.Response execute = Jsoup
                        .connect(url)
                        .timeout(3000)
                        .cookie(VolunteerWebConstant.COOKIE_NAME, cookie)
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
        throw new RuntimeException("访问中国志愿服务网失败，请检查网络是否连通！");
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

        String url = volunteerWebConfig.getDomain() + uri;
        int maxRetryTime = 10;
        int retryTime = 0;

        String cookie = "";
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            cookie = this.login(username, password);
        }

        do {
            try {
                Connection.Response execute = Jsoup
                        .connect(url)
                        .cookie(VolunteerWebConstant.COOKIE_NAME, cookie)
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
        throw new RuntimeException("访问中国志愿服务网失败，请检查网络是否连通！");
    }

    @Override
    public byte[] excute(String uri, Connection.Method method) {
        Connection.Response execute = null;
        try {
            execute = Jsoup
                    .connect(volunteerWebConfig.getDomain() + uri)
                    .ignoreContentType(true)
                    .method(method)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute.bodyAsBytes();
    }
}
