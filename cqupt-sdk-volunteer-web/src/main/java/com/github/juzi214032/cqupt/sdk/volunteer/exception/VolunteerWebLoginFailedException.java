package com.github.juzi214032.cqupt.sdk.volunteer.exception;

/**
 * 登录失败
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/14 13:52
 */
public class VolunteerWebLoginFailedException extends VolunteerWebException {
    public VolunteerWebLoginFailedException() {
    }

    public VolunteerWebLoginFailedException(String message) {
        super(message);
    }

    public VolunteerWebLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerWebLoginFailedException(Throwable cause) {
        super("登录中国志愿服务网失败，检查账号密码是否正确",cause);
    }

    public VolunteerWebLoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
