package com.juzi.cqupt.sdk.oa.api;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * OA基础服务
 *
 * @author Juzi
 * @date 2019/12/28 22:10
 * Blog https://juzibiji.top
 */
public interface OaService {

    /**
     * @param uri
     * @return
     */
    Document get(String uri);

    /**
     * get封装
     *
     * @param uri
     * @param queryData
     * @param headerData
     * @param cookieData
     * @return
     */
    Document get(String uri, Map<String, String> queryData, Map<String, String> headerData, Map<String, String> cookieData);

    Connection.Response excute(Connection.Method method, String uri);
}
