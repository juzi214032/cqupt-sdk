package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeMakeUpExam;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeTotal;

import java.util.List;
import java.util.Map;

/**
 * 成绩查询接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/11 12:23
 */
public interface JwzxGradeService {

    /**
     * 成绩总表url
     */
    String GRADE_TOTAL_URL = "/student/chengjiPm.php";

    /**
     * 当前学期成绩url
     */
    String GRADE_PROCESS_URL = "/student/chengji.php";

    /**
     * 获取当前学期期末成绩
     */
    String GRADE_END_TERM_URL = "/student/chengjiQm.php";

    /**
     * 获取补考成绩
     */
    String GRADE_MAKE_UP_EXAM_URL = "/student/chengjiBk.php";

    /**
     * 获取成绩总表
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    List<JwzxGradeTotal> getGradeTotal(String username, String password);

    /**
     * 获取成绩总表
     *
     * @param username   账号
     * @param password   密码
     * @param creditType 学分类型（A/B）
     * @return
     */
    List<JwzxGradeTotal> getGradeTotal(String username, String password, String creditType);

    /**
     * 获取过程考核成绩（平时成绩）
     *
     * @param username 账号
     * @param password 密码
     * @return 平时成绩列表
     */
    Map<String, Object> getGradeProcess(String username, String password);

    /**
     * 获取当前学期期末成绩
     *
     * @param username 账号
     * @param password 密码
     * @return 期末成绩
     */
    Map<String, Object> getGradeEndTerm(String username, String password);

    /**
     * 获取补考成绩
     *
     * @param username 账号
     * @param password 密码
     * @return 补考成绩
     */
    List<JwzxGradeMakeUpExam> getGradeMakeUpExam(String username, String password);

    /**
     * 获取gpa
     *
     * @param username 账号
     * @param password 密码
     * @return gpa
     */
    String getGpa(String username, String password);
}
