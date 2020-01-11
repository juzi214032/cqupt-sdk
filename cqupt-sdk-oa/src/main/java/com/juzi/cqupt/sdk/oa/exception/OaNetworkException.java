package com.juzi.cqupt.sdk.oa.exception;

/**
 * 网络异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/28 22:33
 */
public class OaNetworkException extends OaBaseException {
    public OaNetworkException(Throwable cause) {
        super("OA服务网络连接异常", cause);
    }
}
