package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxTeacher;

import java.util.List;

/**
 * 教师信息接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/3 14:47
 */
public interface JwzxTeacherInfoService {

    String TEACHER_INFO_URL = "/data/json_teacherSearch.php?searchKey=";

    /**
     * 获取教师信息
     *
     * @param keyword 搜索关键词
     * @return 教师信息
     */
    List<JwzxTeacher> getTeacherInfo(String keyword);

}
