package com.juzi.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 调停课
 *
 * @author Juzi
 * @since 2020/1/5 21:02
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxMediationClass {

    /**
     * 学期
     */
    private String term;
    /**
     * 类型
     * 补课/停课
     */
    private String type;
    /**
     * 教学班id
     */
    private String classId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 教师
     */
    private String teacher;
    /**
     * 停课周次
     */
    private String week;
    /**
     * 停课时间
     */
    private String time;
    /**
     * 补课时间
     */
    private String makeUpTime;
    /**
     * 补课地点
     */
    private String makeUpPlace;
    /**
     * 代课老师
     */
    private String supplyTeacher;

}
