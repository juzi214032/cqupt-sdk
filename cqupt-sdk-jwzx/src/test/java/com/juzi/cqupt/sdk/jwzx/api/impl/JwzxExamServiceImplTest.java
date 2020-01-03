package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxExamService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxCourseExam;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JwzxExamServiceImplTest extends JwzxBaseTest {

    private JwzxExamService jwzxExamService = new JwzxExamServiceImpl(this.jwzxService);

    @Test
    void getCourseExams() {
        List<JwzxCourseExam> courseExams = jwzxExamService.getCourseExams("2017214032", "067678");
        assertNotEquals(0, courseExams.size());
        courseExams.forEach(System.out::println);
    }
}