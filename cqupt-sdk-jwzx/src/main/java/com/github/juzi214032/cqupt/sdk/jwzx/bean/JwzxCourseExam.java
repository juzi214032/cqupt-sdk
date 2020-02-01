package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程考试
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/1 23:25
 */
@Data
@Accessors(chain = true)
public class JwzxCourseExam implements Serializable {

    private static final long serialVersionUID = 2193080798595100353L;
    /**
     * 编号
     */
    private Integer no;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 考试类型
     */
    private String examType;

    /**
     * 课程编号
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 考试周次
     */
    private String examWeekNo;

    /**
     * 星期几
     */
    private String examDay;

    /**
     * 考试时间
     */
    private String examTime;

    /**
     * 考试地点
     */
    private String examPlace;

    /**
     * 考生座位
     */
    private String examSeat;

    /**
     * 考试资格
     */
    private String examStatus;
}
