package com.github.juzi214032.cqupt.sdk.volunteer.exception;

/**
 * 中国志愿服务网异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/8 22:35
 */
public class VolunteerWebException extends RuntimeException {

    public VolunteerWebException() {
    }

    public VolunteerWebException(String message) {
        super(message);
    }

    public VolunteerWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolunteerWebException(Throwable cause) {
        super(cause);
    }

    public VolunteerWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
