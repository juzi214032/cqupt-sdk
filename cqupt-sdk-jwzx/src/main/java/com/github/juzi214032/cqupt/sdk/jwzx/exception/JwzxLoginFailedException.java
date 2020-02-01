package com.github.juzi214032.cqupt.sdk.jwzx.exception;

/**
 * 登录失败
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/2 12:49
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
