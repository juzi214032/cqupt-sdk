package com.juzi.cqupt.sdk.jwzx.api;

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

    String STUDENT_COURSE_TABLE_URL = "/kebiao/kb_stu.php?xh=";

    String TEACHER_COURSE_TABLE_URL = "/kebiao/kb_tea.php?teaId=";

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

}
