package com.juzi.cqupt.sdk.oa.exception;

/**
 * @author Juzi
 * @since 2019/12/28 22:31
 * Blog https://juzibiji.top
 */
public class OaBaseException extends RuntimeException {

    public OaBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OaBaseException(Throwable cause) {
        super(cause);
    }
}
