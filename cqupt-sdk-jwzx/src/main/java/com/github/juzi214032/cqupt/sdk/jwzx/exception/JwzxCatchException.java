package com.github.juzi214032.cqupt.sdk.jwzx.exception;

/**
 * 需要捕获的异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/3 19:00
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
