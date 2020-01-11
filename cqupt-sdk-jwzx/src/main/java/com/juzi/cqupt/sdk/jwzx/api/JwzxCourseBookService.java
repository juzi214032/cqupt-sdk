package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxCourseBook;

import java.util.List;

/**
 * 教材信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/2 18:46
 */
public interface JwzxCourseBookService {

    String COURSE_BOOK_URL = "/student/jiaocai.php";

    /**
     * 获取教材信息
     *
     * @param studentId 学号
     * @param password  教务在线密码
     * @return 教材列表
     */
    List<JwzxCourseBook> getCourseBooks(String studentId, String password);

}
