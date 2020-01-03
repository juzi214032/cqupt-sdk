package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxStudentInfoService;
import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudent;
import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudentClass;
import com.juzi.cqupt.sdk.jwzx.bean.student.JwzxStudentExtra;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwzxStudentInfoServiceImplTest extends JwzxBaseTest {

    private JwzxStudentInfoService jwzxStudentInfoService = new JwzxStudentInfoServiceImpl(jwzxService);

    @Test
    void getStudetInfo() throws IOException {
        List<JwzxStudent> studentInfoList = jwzxStudentInfoService.getStudetInfo("赵令馨");
        assertNotEquals(0, studentInfoList.size());
        for (JwzxStudent student : studentInfoList) {
            System.out.println(student);
        }
    }

    @Test
    void getStudentInfoExtra() throws IOException {
        JwzxStudentExtra studentInfoExtra = jwzxStudentInfoService.getStudentInfoExtra("2017214032", "067678");
        assertNotNull(studentInfoExtra);
        System.out.println(studentInfoExtra);
    }

    @Test
    void getClassStudentList() {
        List<JwzxStudentClass> classStudentList = jwzxStudentInfoService.getClassStudentList("A00191A1110140006");
        assertNotEquals(0, classStudentList.size(), "获取教学班学生列表失败");
        classStudentList.forEach(System.out::println);

    }
}
