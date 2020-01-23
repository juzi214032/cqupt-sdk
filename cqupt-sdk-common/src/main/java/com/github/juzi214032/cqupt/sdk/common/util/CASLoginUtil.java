package com.github.juzi214032.cqupt.sdk.common.util;

import com.github.juzi214032.cqupt.sdk.common.enums.CASService;
import com.github.juzi214032.cqupt.sdk.common.exception.CASLoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单点登录工具类
 * 重庆邮电大学统一身份认证登录
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/27 21:00
 */
@Slf4j
public class CASLoginUtil {

    /**
     * 登录成功的host
     */
    private static final String LOGIN_SUCCESS_HOST = "ids.cqupt.edu.cn";

    /**
     * @param username 账号-统一身份认证码
     * @param password 密码-默认身份证后六位
     * @param service  单点服务类型
     * @return 登录成功的cookie
     */
    public static Map<String, String> login(String username, String password, CASService service) throws CASLoginFailedException {
        // 最大重试次数
        int maxRetry = 3;

        // 查询参数
        HashMap<String, String> queryData = new HashMap<>(1);
        queryData.put("service", service.getUrl());

        Connection.Response getResponse = null;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                getResponse = HttpUtil.excute(Connection.Method.GET, "https://ids.cqupt.edu.cn/authserver/login", queryData, null, null, null, 1000 * 3);
                break;
            } catch (IOException e) {
                if (i == maxRetry) {
                    log.warn("网络连接失败：连接url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i, e);
                    throw new CASLoginFailedException(e);
                } else {
                    log.info("网络连接失败：连接url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i);
                }
            }
        }

        // 登录所需的body参数
        Document getDocument = null;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                getDocument = getResponse.parse();
                break;
            } catch (IOException e) {
                if (i == maxRetry) {
                    log.warn("响应数据解析失败：解析url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i, e);
                    throw new CASLoginFailedException(e);
                } else {
                    log.info("响应数据解析失败：解析url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i);
                }
            }
        }

        String lt = getDocument.select("[name=lt]").val();
        String execution = getDocument.select("[name=execution]").val();
        String eventId = getDocument.select("[name=_eventId]").val();
        String rmShown = getDocument.select("[name=rmShown]").val();
        Map<String, String> bodyData = new HashMap<>(6);
        bodyData.put("username", username);
        bodyData.put("password", password);
        bodyData.put("lt", lt);
        bodyData.put("execution", execution);
        bodyData.put("_eventId", eventId);
        bodyData.put("rmShown", rmShown);

        //登录所需cookie
        Map<String, String> cookieData = getResponse.cookies();

        Connection.Response postResponse = null;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                postResponse = HttpUtil.excute(Connection.Method.POST, "https://ids.cqupt.edu.cn/authserver/login", queryData, null, cookieData, HttpUtil.convertMapToUrlencoded(bodyData), 1000 * 3);
                break;
            } catch (IOException e) {
                if (i == maxRetry) {
                    log.warn("网络连接：连接url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i, e);
                    throw new CASLoginFailedException(e);
                } else {
                    log.info("网络连接：连接url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i);
                }
            }
        }

        Document postDocument = null;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                postDocument = postResponse.parse();
                break;
            } catch (IOException e) {
                if (i == maxRetry) {
                    log.warn("响应数据解析失败：解析url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i, e);
                    throw new CASLoginFailedException(e);
                } else {
                    log.info("响应数据解析失败：解析url为：https://ids.cqupt.edu.cn/authserver/login，当前第[{}]次尝试", i);
                }
            }
        }

        if (LOGIN_SUCCESS_HOST.equals(postResponse.url().getHost())) {
            String loginFailTips = postDocument.select("#msg").val();
            log.info("CAS登录失败，服务[{}]，账号：[{}]，密码：[{}]，失败原因：[{}]", service.getUrl(), username, password, loginFailTips);
            throw new CASLoginFailedException("CAS登录失败");
        }

        return postResponse.cookies();
    }
}
