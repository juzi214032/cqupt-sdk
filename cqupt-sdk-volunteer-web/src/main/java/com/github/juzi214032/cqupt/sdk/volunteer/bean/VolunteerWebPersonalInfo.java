package com.github.juzi214032.cqupt.sdk.volunteer.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 个人信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 13:22
 */
@Data
@Accessors(chain = true)
public class VolunteerWebPersonalInfo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 志愿者编号
     */
    private String volunteerId;

    /**
     * 服务时长
     */
    private String time;

    /**
     * 加入志愿团体个数
     */
    private Integer organizationCount;

    /**
     * 参与志愿项目个数
     */
    private Integer projectCount;
}