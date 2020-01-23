package com.github.juzi214032.cqupt.sdk.oa.exception;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/28 22:31
 */
public class OaBaseException extends RuntimeException {

    public OaBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OaBaseException(Throwable cause) {
        super(cause);
    }
}
