package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 调停课
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/5 21:02
 */
@Data
@Accessors(chain = true)
public class JwzxMediationClass implements Serializable {

    private static final long serialVersionUID = -7713664961985069220L;
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
