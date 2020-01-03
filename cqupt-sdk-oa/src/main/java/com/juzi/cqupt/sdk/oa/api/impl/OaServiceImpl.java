package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.common.util.HttpUtil;
import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.config.OaConfig;
import com.juzi.cqupt.sdk.oa.exception.OaNetworkException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @author Juzi
 * @since 2019/12/28 22:16
 * Blog https://juzibiji.top
 */
@Slf4j
public class OaServiceImpl implements OaService {

    private OaConfig oaConfig;

    public OaServiceImpl(OaConfig oaConfig) {
        this.oaConfig = oaConfig;
    }

    @Override
    public Document get(String uri) {
        return this.get(uri, null, null, null);
    }

    @Override
    public Document get(String uri, Map<String, String> queryData, Map<String, String> headerData, Map<String, String> cookieData) {
        // 拼接url
        String url = oaConfig.getDomain() + uri;
        for (int i = 1; i <= oaConfig.getMaxRetryTimes(); i++) {
            try {
                return HttpUtil.get(url, queryData, headerData, cookieData, oaConfig.getTimeout());
            } catch (IOException e) {
                if (i == oaConfig.getMaxRetryTimes()) {
                    log.warn("请求OA服务失败，访问url[{}]", url, e);
                    throw new OaNetworkException(e);
                } else {
                    log.info("请求OA服务失败，访问url[{}]", url);
                }
            }
        }

        // 理论上不会到达该return，但不知编译器为何报错，所以加上此句
        log.error("出现不可控异常，程序执行到不应执行的位置，请立即检查");
        return null;
    }

    @Override
    public Connection.Response excute(Connection.Method method, String uri) {
        // url
        String url = oaConfig.getDomain() + uri;
        // 最大访问次数
        Integer maxRetryTimes = oaConfig.getMaxRetryTimes();
        for (int i = 1; i <= maxRetryTimes; i++) {
            try {
                return HttpUtil.excute(method, url, null, null, null, null, oaConfig.getTimeout());
            } catch (IOException e) {
                if (i == maxRetryTimes) {
                    log.warn("请求OA服务失败，访问url[{}]", url, e);
                    throw new OaNetworkException(e);
                } else {
                    log.info("请求OA服务失败，访问url[{}]", url);
                }
            }
        }

        // 理论上不会到达该return，但不知编译器为何报错，所以加上此句
        log.error("出现不可控异常，程序执行到不应执行的位置，请立即检查");
        return null;
    }
}
