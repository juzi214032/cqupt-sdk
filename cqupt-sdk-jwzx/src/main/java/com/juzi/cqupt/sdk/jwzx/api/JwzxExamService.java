package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxCourseExam;

import java.util.List;

/**
 * 考试安排
 *
 * @author Juzi - https://juzibiji.top
 * @date 2019/12/1 23:22
 */
public interface JwzxExamService {

    /**
     * 课程考试
     */
    String COURSE_EXAM_URL = "/student/ksap.php";

    /**
     * 获取课程考试
     *
     * @param username 账号
     * @param password 密码
     * @return 考试列表
     */
    List<JwzxCourseExam> getCourseExams(String username, String password);
}
