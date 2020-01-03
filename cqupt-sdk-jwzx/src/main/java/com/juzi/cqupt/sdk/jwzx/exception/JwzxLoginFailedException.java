package com.juzi.cqupt.sdk.jwzx.exception;

/**
 * 登录失败
 *
 * @author Juzi
 * @since 2019/8/2 12:49
 * Blog https://juzibiji.top
 */
public class JwzxLoginFailedException extends JwzxException {
    public JwzxLoginFailedException() {
        super("登录失败，请确认账号密码是否正确");
    }
}
