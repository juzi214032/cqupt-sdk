package com.juzi.cqupt.sdk.common.exception;

/**
 * SDK common模块底层异常
 *
 * @author Juzi
 * @since 2019/12/28 19:21
 * Blog https://juzibiji.top
 */
public class SDKCommonBaseException extends RuntimeException {
    public SDKCommonBaseException(String message) {
        super(message);
    }

    public SDKCommonBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
