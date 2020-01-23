package com.github.juzi214032.cqupt.sdk.library.exception;

/**
 * 自定义异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/17 22:02
 */
public class LibBaseException extends RuntimeException {
    public LibBaseException(String message) {
        super(message);
    }

    public LibBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
