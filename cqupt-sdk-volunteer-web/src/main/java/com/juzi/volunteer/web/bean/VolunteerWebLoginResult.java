package com.juzi.volunteer.web.bean;

import lombok.Data;

/**
 * 登录结果
 *
 * @author Juzi
 * @since 2019/8/8 22:32
 * Blog https://juzibiji.top
 */
@Data
public class VolunteerWebLoginResult {

    /**
     * 代码
     */
    private String code;

    /**
     * 信息提示
     */
    private String msg;
}
