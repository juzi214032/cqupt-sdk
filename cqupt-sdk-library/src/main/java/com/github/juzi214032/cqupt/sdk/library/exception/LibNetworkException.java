package com.github.juzi214032.cqupt.sdk.library.exception;

/**
 * 连接图书馆网站失败
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/23 16:49
 */
public class LibNetworkException extends LibBaseException {
    public LibNetworkException() {
        super("网络连接失败");
    }

    public LibNetworkException(String message) {
        super(message);
    }

    public LibNetworkException(Throwable cause) {
        super("网络连接失败", cause);
    }

    public LibNetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
