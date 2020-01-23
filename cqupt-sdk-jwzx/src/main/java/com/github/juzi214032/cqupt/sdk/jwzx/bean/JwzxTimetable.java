package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课表上的课程
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/6 17:11
 */
@Data
@Accessors(chain = true)
public class JwzxTimetable {

    /**
     * 教学班id
     */
    private String classId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 上课地点
     */
    private String place;

    /**
     * 上课周次（显示数据）
     */
    private String weekView;

    /**
     * 上课周次（二进制数据）
     */
    private String weekBin;

    /**
     * 几节连上
     */
    private Integer duration;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 课程类型（必修/选修）
     */
    private String type;

    /**
     * 学分
     */
    private String credits;

    /**
     * 课程起始节数编号（1、3、5、7、9、11）
     */
    private Integer courseStartNo;

    /**
     * 课程是周几（1-7）
     */
    private Integer courseWeek;
}
