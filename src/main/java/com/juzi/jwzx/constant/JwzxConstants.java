package com.juzi.jwzx.constant;

/**
 * 教务在线相关常量
 *
 * @author Juzi
 * @date 2019/8/1 22:24
 * Blog https://juzibiji.top
 */
public class JwzxConstants {
    /**
     * cookie名
     */
    public static final String COOKIE_NAME = "PHPSESSID";
    
    /**
     * 登录接口参数名
     */
    public static class LoginParam {
        /**
         * 用户名
         */
        public static final String USERNAME = "name";
        
        /**
         * 密码
         */
        public static final String PASSWORD = "password";
        
        /**
         * 验证码
         */
        public static final String AUTH_CODE = "vCode";
    }
    
    public static class LoginResultCode {
        
        /**
         * 登录成功
         */
        public static final String LOGIN_SUCCESS = "0";
        
        /**
         * 登录失败
         * 账号密码错误或验证码错误
         */
        public static final String LOGIN_FAILED = "1";
    }
}
