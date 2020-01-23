package com.github.juzi214032.cqupt.sdk.volunteer.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 时长记录
 *
 * @author Juzi - https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class VolunteerWebHours {

    /**
     * 时长
     */
    private String hours;

    /**
     * 备注
     */
    private String note;

    /**
     * 添加方式
     */
    private String addWay;

    /**
     * 状态
     */
    private String hoursStatus;

    /**
     * 项目
     */
    private String project;

    /**
     * 团体
     */
    private String organization;

    /**
     * 日期
     */
    private String date;
}
