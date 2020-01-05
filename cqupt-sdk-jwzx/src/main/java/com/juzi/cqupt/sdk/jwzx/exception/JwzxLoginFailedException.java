package com.juzi.cqupt.sdk.jwzx.exception;

/**
 * 登录失败
 *
 * @author Juzi
 * @since 2019/8/2 12:49
 * Blog https://juzibiji.top
 */
public class JwzxLoginFailedException extends RuntimeException {
    public JwzxLoginFailedException() {
        super("登录失败，请确认账号密码是否正确");
    }

    public JwzxLoginFailedException(String message) {
        super(message);
    }

    public JwzxLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwzxLoginFailedException(Throwable cause) {
        super("登录失败，请确认账号密码是否正确", cause);
    }

    public JwzxLoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
