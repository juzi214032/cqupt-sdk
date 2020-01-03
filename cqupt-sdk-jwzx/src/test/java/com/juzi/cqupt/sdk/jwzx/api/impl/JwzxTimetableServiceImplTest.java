package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.alibaba.fastjson.JSON;
import com.juzi.cqupt.sdk.jwzx.api.JwzxTimetableService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxTimetable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JwzxTimetableServiceImplTest extends JwzxBaseTest {

    private JwzxTimetableService jwzxTimetableService = new JwzxTimetableServiceImpl(jwzxService);

    @Test
    void getStudentCourseTable() throws IOException {
        List<JwzxTimetable> studentCourseTable = jwzxTimetableService.getStudentTimetable("2017214032");
        assertNotEquals(0, studentCourseTable.size(), "课表获取失败");
        // studentCourseTable.forEach(System.out::println);
        String json = JSON.toJSONString(studentCourseTable);
        System.out.println(json);
    }

    @Test
    void getTeacherCourseTable() {
        List<JwzxTimetable> teacherCourseTable = jwzxTimetableService.getTeacherTimetable("130716");
        assertNotEquals(0, teacherCourseTable.size(), "课表获取失败");
        teacherCourseTable.forEach(System.out::println);
    }
}
