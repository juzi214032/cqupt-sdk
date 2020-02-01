package com.github.juzi214032.cqupt.sdk.common.exception;

/**
 * SDK common模块底层异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/28 19:21
 */
public class SDKCommonBaseException extends RuntimeException {
    public SDKCommonBaseException(String message) {
        super(message);
    }

    public SDKCommonBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
