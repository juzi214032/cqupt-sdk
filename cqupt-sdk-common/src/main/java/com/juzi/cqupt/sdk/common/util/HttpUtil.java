package com.juzi.cqupt.sdk.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求工具
 *
 * @author Juzi
 * @since 2019/12/27 20:49
 * Blog https://juzibiji.top
 */
@Slf4j
public class HttpUtil {

    /**
     * get请求封装
     *
     * @param url        完整url
     * @param queryData  查询参数
     * @param headerData 请求头参数
     * @param cookieData cookie
     * @param timeout    超时时间
     * @return 文档对象
     * @throws IOException http连接异常
     */
    public static Document get(String url, Map<String, String> queryData, Map<String, String> headerData, Map<String, String> cookieData, int timeout) throws IOException {
        Connection.Response response = HttpUtil.excute(Connection.Method.GET, url, queryData, headerData, cookieData, null, timeout);
        return response.parse();
    }

    /**
     * post请求封装
     *
     * @param url        完整url
     * @param queryData  查询参数
     * @param headerData 请求头参数
     * @param cookieData cookie
     * @param bodyData   请求体参数
     * @param timeout    超时时间
     * @return 文档对象
     * @throws IOException
     */
    public static Document post(String url, Map<String, String> queryData, Map<String, String> headerData, Map<String, String> cookieData, String bodyData, int timeout) throws IOException {
        Connection.Response response = HttpUtil.excute(Connection.Method.POST, url, queryData, headerData, cookieData, bodyData, timeout);
        return response.parse();
    }

    /**
     * 底层通用请求封装
     *
     * @param method     请求方法
     * @param url        完整url链接
     * @param queryData  查询参数-拼接在url后面
     * @param headerData 请求头参数
     * @param cookieData cookie
     * @param bodyData   请求体参数
     * @param timeout    超时时间
     * @return Connection.Response对象
     * @throws IOException http连接异常
     */
    public static Connection.Response excute(
            Connection.Method method,
            String url,
            Map<String, String> queryData,
            Map<String, String> headerData,
            Map<String, String> cookieData,
            String bodyData, int timeout) throws IOException {

        if (headerData == null) {
            headerData = new HashMap<>(0);
        }

        if (queryData == null) {
            queryData = new HashMap<>(0);
        }

        if (cookieData == null) {
            cookieData = new HashMap<>(0);
        }

        return Jsoup
                .connect(url)
                .method(method)
                .headers(headerData)
                .cookies(cookieData)
                .data(queryData)
                .requestBody(bodyData)
                .timeout(timeout)
                .execute();
    }

    /**
     * 把Map数据编码为x-www-form-urlencoded格式数据
     *
     * @param data Map数据
     * @return x-www-form-urlencoded格式数据
     */
    public static String convertMapToUrlencoded(Map<String, String> data) {
        StringBuilder stringBuilder = new StringBuilder();

        data.forEach((key, value) -> {
            try {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("x-www-form-urlencoded编码错误", e);
                return;
            }

            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(value);
            stringBuilder.append("&");
        });

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
