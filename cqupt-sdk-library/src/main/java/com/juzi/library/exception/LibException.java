package com.juzi.library.exception;

/**
 * 自定义异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/17 22:02
 */
public class LibException extends RuntimeException {
    public LibException(String message) {
        super(message);
    }
}
