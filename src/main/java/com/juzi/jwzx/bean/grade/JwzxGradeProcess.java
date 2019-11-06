package com.juzi.jwzx.bean.grade;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 过程（平时）成绩
 *
 * @author Juzi
 * @date 2019/9/11 13:19
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxGradeProcess {
    
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
    public class JwzxGradeProcessDetail {
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
