package com.github.juzi214032.cqupt.sdk.volunteer.api;

import com.github.juzi214032.cqupt.sdk.volunteer.config.VolunteerWebConfig;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 中国志愿网接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/8 21:29
 */
public interface VolunteerWebService {

    /**
     * 登录url
     */
    String LOGIN_PAGE_URL = "/app/user/login.php";

    /**
     * 登录api
     */
    String LOGIN_API_URL = "/app/user/login.php?m=login";

    /**
     * 获取配置类对象
     *
     * @return 配置类对象
     */
    VolunteerWebConfig getConfig();

    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return cookie
     */
    String login(String username, String password);

    /**
     * 登录
     *
     * @param username    账号
     * @param password    密码
     * @param forceUpdate 强制更新（不使用缓存）
     * @return 登录成功的cookie
     */
    String login(String username, String password, boolean forceUpdate);

    /**
     * 通用get请求
     *
     * @param uri 网址路径
     * @return
     */
    Document get(String uri);

    /**
     * 通用get请求
     *
     * @param uri      网址路径
     * @param username 教务在线账号
     * @param password 教务在线密码
     * @return
     */
    Document get(String uri, String username, String password);

    /**
     * 通用get请求
     *
     * @param uri  网址路径
     * @param data 携带参数
     * @return html文档
     */
    Document get(String uri, Map<String, String> data);

    /**
     * 通用get请求
     *
     * @param uri      网址路径
     * @param username 账号
     * @param password 密码
     * @param data     携带参数
     * @return html文档
     */
    Document get(String uri, String username, String password, Map<String, String> data);

    /**
     * 通用post请求
     *
     * @param uri 网址路径
     * @return html文档
     */
    Document post(String uri);

    /**
     * 通用post请求
     *
     * @param uri      网址路径
     * @param username 账号
     * @param password 密码
     * @return html文档
     */
    Document post(String uri, String username, String password);

    /**
     * 通用post请求
     *
     * @param uri  网址路径
     * @param data 携带参数
     * @return html文档
     */
    Document post(String uri, Map<String, String> data);

    /**
     * 通用post请求
     *
     * @param uri      网址路径
     * @param username 账号
     * @param password 密码
     * @param data     携带参数
     * @return html文档
     */
    Document post(String uri, String username, String password, Map<String, String> data);

    /**
     * 最底层的请求
     *
     * @param uri
     * @param method
     * @return
     */
    byte[] excute(String uri, Connection.Method method);
}
