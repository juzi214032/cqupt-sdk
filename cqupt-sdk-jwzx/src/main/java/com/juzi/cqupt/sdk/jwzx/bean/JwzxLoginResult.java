package com.juzi.cqupt.sdk.jwzx.bean;

import lombok.Data;

/**
 * 教务在线登陆接口响应结果
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 18:35
 */
@Data
public class JwzxLoginResult {

    /**
     * 响应状态码
     * 0-成功
     * 1-验证码错误/账号密码错误
     */
    private String code;

    /**
     * 响应消息
     */
    private String info;

}
