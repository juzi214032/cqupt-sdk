package com.juzi.cqupt.sdk.jwzx.bean.grade;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 成绩总表
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/11 12:08
 */
@Data
@Accessors(chain = true)
public class JwzxGradeTotal {
    /**
     * 学期
     */
    private String term;

    /**
     * 课程编号
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 类型
     * 选修/必修
     */
    private String type;

    /**
     * 分数
     */
    private String credit;

    /**
     * 考试性质
     */
    private String examNature;

    /**
     * 成绩
     */
    private String grade;

    /**
     * 绩点
     */
    private String gradePoint;

    /**
     * 随课实验成绩
     */
    private String experimentGrade;

    /**
     * 选修课程类型
     * 人文/自然
     */
    private String electiveCourseType;

    /**
     * 是否是跨专业课程
     */
    private Boolean isCrossMajor;

    /**
     * 是否计算绩点
     */
    private Boolean isCountGradePoint;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否计算学分
     */
    private Boolean isCountCredit;

}
