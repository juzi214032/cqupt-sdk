package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 教务在线登陆接口响应结果
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 18:35
 */
@Data
public class JwzxLoginResult implements Serializable {

    private static final long serialVersionUID = 8825598003206528258L;
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
