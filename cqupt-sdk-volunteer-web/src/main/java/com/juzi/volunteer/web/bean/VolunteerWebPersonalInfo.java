package com.juzi.volunteer.web.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 个人信息
 *
 * @author Juzi
 * @since 2019/8/9 13:22
 * Blog https://juzibiji.top
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
