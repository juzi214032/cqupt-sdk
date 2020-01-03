package com.juzi.cqupt.sdk.jwzx.exception;

/**
 * 需要捕获的异常
 *
 * @author Juzi
 * @since 2020/1/3 19:00
 * Blog https://juzibiji.top
 */
public class JwzxCatchException extends Exception {
    public JwzxCatchException() {
    }

    public JwzxCatchException(String message) {
        super(message);
    }

    public JwzxCatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwzxCatchException(Throwable cause) {
        super(cause);
    }

    public JwzxCatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
