package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.common.exception.CASLoginFailedException;
import com.github.juzi214032.cqupt.sdk.jwzx.config.JwzxConfig;
import com.github.juzi214032.cqupt.sdk.jwzx.exception.JwzxLoginFailedException;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * 教务在线公用服务
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 19:47
 */
public interface JwzxService {

    /**
     * 登录验证码链接
     */
    String AUTH_CODE_IMG_URL = "/createValidationCode.php";

    /**
     * 登录链接
     */
    String LOGIN_URL = "/checkLogin.php";

    /**
     * 获取配置对象
     *
     * @return 配置类实例
     */
    JwzxConfig getConfig();

    /**
     * 登录
     *
     * @param username 学号
     * @param password 密码/初始为身份证后6位
     * @return 认证后的cookie
     */
    String login(String username, String password);

    /**
     * 登录
     *
     * @param username    账号
     * @param password    密码
     * @param forceUpdate 是否强制更新（强制更新不会使用缓存）
     * @return
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
     * @param username 教务在线账号
     * @param password 教务在线密码
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
     * @param username 教务在线账号
     * @param password 教务在线密码
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
     * @param username 教务在线账号
     * @param password 教务在线密码
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
    Connection.Response excute(String uri, Connection.Method method);

    /**
     * 通用请求封装
     *
     * @param method 请求方法
     * @param url    完整url链接
     * @return Connection.Response对象
     */
    Connection.Response excute(Connection.Method method, String url);

    /**
     * 通用请求封装
     *
     * @param method     请求方法
     * @param url        完整url链接
     * @param queryData  查询参数-拼接在url后面
     * @param headerData 请求头参数
     * @param cookieData cookie
     * @param bodyData   请求体参数
     * @return Connection.Response对象
     */
    Connection.Response excute(Connection.Method method,
                               String url,
                               Map<String, String> queryData,
                               Map<String, String> headerData,
                               Map<String, String> cookieData,
                               String bodyData);

    /**
     * 使用统一身份认证登录
     *
     * @param username 统一识别码
     * @param password 密码-默认身份证后6位
     * @return 登录成功的cookie
     */
    String loginByCAS(String username, String password) throws CASLoginFailedException;
}
