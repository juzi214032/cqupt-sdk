package com.github.juzi214032.cqupt.sdk.volunteer.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录结果
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/8 22:32
 */
@Data
public class VolunteerWebLoginResult implements Serializable {

    private static final long serialVersionUID = 2878762688558369272L;
    /**
     * 代码
     */
    private String code;

    /**
     * 信息提示
     */
    private String msg;
}
