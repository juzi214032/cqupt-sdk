package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxMediationClass;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxTimetable;

import java.util.List;

/**
 * 课表接口
 *
 * @author Juzi
 * @since 2019/9/6 17:09
 * Blog https://juzibiji.top
 */
public interface JwzxTimetableService {

    /**
     * 学生课表url
     */
    String STUDENT_COURSE_TABLE_URL = "/kebiao/kb_stu.php?xh=";

    /**
     * 教师课表url
     */
    String TEACHER_COURSE_TABLE_URL = "/kebiao/kb_tea.php?teaId=";

    /**
     * 调停课url
     */
    String MEDIATION_CLASS_URL = "/kebiao/kb_stu.php?xh=";

    /**
     * 获取学生课表
     *
     * @param studentId 学号
     * @return
     */
    List<JwzxTimetable> getStudentTimetable(String studentId);

    /**
     * 获取教师课表
     *
     * @param teacherId
     * @return
     */
    List<JwzxTimetable> getTeacherTimetable(String teacherId);

    /**
     * 获取调停课课表
     *
     * @param studentId 学号
     * @return 调停课
     */
    List<JwzxMediationClass> getMediationClasses(String studentId);
}
