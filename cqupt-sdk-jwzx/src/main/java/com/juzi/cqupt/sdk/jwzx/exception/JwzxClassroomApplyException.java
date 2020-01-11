package com.juzi.cqupt.sdk.jwzx.exception;

/**
 * 教室申请异常
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/3 19:01
 */
public class JwzxClassroomApplyException extends JwzxCatchException {
    public JwzxClassroomApplyException() {
    }

    public JwzxClassroomApplyException(String message) {
        super(message);
    }

    public JwzxClassroomApplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwzxClassroomApplyException(Throwable cause) {
        super(cause);
    }

    public JwzxClassroomApplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
