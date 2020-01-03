package com.juzi.cqupt.sdk.oa.exception;

/**
 * 网络异常
 *
 * @author Juzi
 * @since 2019/12/28 22:33
 * Blog https://juzibiji.top
 */
public class OaNetworkException extends OaBaseException {
    public OaNetworkException(Throwable cause) {
        super("OA服务网络连接异常", cause);
    }
}
