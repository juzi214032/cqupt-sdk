package com.juzi.cqupt.sdk.jwzx.bean.grade;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 补考成绩
 *
 * @author Juzi
 * @since 2019/9/14 15:37
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxGradeMakeUpExam {
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
     * 教学班编号
     */
    private String classId;

    /**
     * 课程类别
     */
    private String courseType;
    /**
     * 考试性质
     */
    private String testNature;
    /**
     * 最终成绩
     */
    private String finalGrade;
}
