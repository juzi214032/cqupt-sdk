package com.github.juzi214032.cqupt.sdk.jwzx.bean.grade;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 期末成绩
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/14 14:46
 */
@Data
@Accessors(chain = true)
public class JwzxGradeEndTerm implements Serializable {
    private static final long serialVersionUID = 5394280509906039986L;
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
     * 修读状态
     */
    private String studyStatus;

    /**
     * 平时成绩（折算后）
     */
    private String processGrade;

    /**
     * 考试性质
     */
    private String testNature;

    /**
     * 期末考核成绩
     */
    private String endTermGrade;

    /**
     * 期末成绩比例
     */
    private String endTermGradeRatio;

    /**
     * 最终成绩
     */
    private String finalGrade;
}
