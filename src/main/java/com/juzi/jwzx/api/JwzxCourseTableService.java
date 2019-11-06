package com.juzi.jwzx.api;

import com.juzi.jwzx.bean.JwzxCourseTable;

import java.util.List;

/**
 * 课表接口
 *
 * @author Juzi
 * @date 2019/9/6 17:09
 * Blog https://juzibiji.top
 */
public interface JwzxCourseTableService {
    
    String STUDENT_COURSE_TABLE_URL = "/kebiao/kb_stu.php?xh=";
    
    String TEACHER_COURSE_TABLE_URL = "/kebiao/kb_tea.php?teaId=";
    
    /**
     * 获取学生课表
     *
     * @param studentId 学号
     * @return
     */
    List<JwzxCourseTable> getStudentCourseTable(String studentId);
    
    /**
     * 获取教师课表
     *
     * @param teacherId
     * @return
     */
    List<JwzxCourseTable> getTeacherCourseTable(String teacherId);
    
}
