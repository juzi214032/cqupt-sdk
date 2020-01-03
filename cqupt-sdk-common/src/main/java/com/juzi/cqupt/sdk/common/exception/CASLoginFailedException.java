package com.juzi.cqupt.sdk.common.exception;

/**
 * CAS登录失败异常
 *
 * @author Juzi
 * @since 2019/12/28 19:23
 * Blog https://juzibiji.top
 */
public class CASLoginFailedException extends Exception {
    public CASLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CASLoginFailedException(String message) {
        super(message);
    }

    public CASLoginFailedException(Throwable cause) {
        super("CAS登录失败", cause);
    }

    public CASLoginFailedException() {
        super("CAS登录失败");
    }
}
