package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudent;
import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudentClass;
import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudentExtra;

import java.util.List;

/**
 * 学生信息获取
 *
 * @author Juzi
 * @since 2019/8/1 21:27
 * Blog https://juzibiji.top
 */
public interface JwzxStudentInfoService {

    /**
     * 学生基本信息
     */
    String STUDENT_INFO_URL = "/data/json_StudentSearch.php?searchKey=";

    /**
     * 学生扩展信息
     */
    String STUDENT_INFO_EXTRA_URL = "/user.php";

    /**
     * 教学班学生列表
     */
    String CLASS_STUDENT_URL = "/kebiao/kb_stuList.php?jxb=";

    /**
     * 学生照片
     */
    String STUDENT_PHOTO_URL = "/showstupic.php?xh=";

    /**
     * 获取学生基本信息
     *
     * @param keyword 搜索关键字（学号/姓名）
     * @return 学生基本信息
     */
    List<JwzxStudent> getStudetInfo(String keyword);

    /**
     * 获取学生扩展信息
     * 比基本信息多：email+统一认证码
     *
     * @param studentId 学号
     * @param password  教务在线密码
     * @return 学生扩展信息
     */
    JwzxStudentExtra getStudentInfoExtra(String studentId, String password);

    /**
     * 获取教学班学生列表
     *
     * @param classId 教学班id
     * @return 学生列表
     */
    List<JwzxStudentClass> getClassStudentList(String classId);

    /**
     * 获取学生照片
     *
     * @param studentId 学号
     * @return 二进制数组
     */
    byte[] getStudentPhoto(String studentId);
}
