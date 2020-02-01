package com.github.juzi214032.cqupt.sdk.jwzx.bean.grade;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 过程（平时）成绩
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/11 13:19
 */
@Data
@Accessors(chain = true)
public class JwzxGradeProcess implements Serializable {

    private static final long serialVersionUID = 4154541868468583389L;
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
     * 成绩列表
     */
    private List<JwzxGradeProcessDetail> gradeDetails;

    @Data
    @Accessors(chain = true)
    public class JwzxGradeProcessDetail implements Serializable {
        private static final long serialVersionUID = -8102237937611553223L;
        /**
         * 成绩上报教师
         */
        private String teacher;

        /**
         * 考核题目
         */
        private String examinTitle;

        /**
         * 考核类型
         */
        private String examinType;

        /**
         * 成绩
         */
        private String grade;

        /**
         * 成绩比例
         */
        private String gradeRatio;
    }

}
