package com.juzi.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 授课计划
 *
 * @author Juzi
 * @since 2020/1/9 10:26
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxCoursePlan {

    /**
     * 教学班
     */
    private String classId;
    /**
     * 课程编号
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 类别
     */
    private String courseType;
    /**
     * 课程分类
     */
    private String courseSort;
    /**
     * 成绩组成
     */
    private String gradeComposition;
}
