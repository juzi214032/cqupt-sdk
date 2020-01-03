package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxGradeService;
import com.juzi.cqupt.sdk.jwzx.bean.grade.JwzxGradeMakeUpExam;
import com.juzi.cqupt.sdk.jwzx.bean.grade.JwzxGradeTotal;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwzxGradeServiceImplTest extends JwzxBaseTest {

    private JwzxGradeService jwzxGradeService = new JwzxGradeServiceImpl(jwzxService);

    @Test
    void getGradeTotal() {
        List<JwzxGradeTotal> gradeList = jwzxGradeService.getGradeTotal("2017214032", "067678", "A");
        assertNotEquals(0, gradeList.size(), "获取成绩总表失败！");
        gradeList.forEach(System.out::println);
    }

    @Test
    void getGradeProcess() {
        Map<String, Object> gradeProcess = jwzxGradeService.getGradeProcess("2017214032", "067678");
        assertNotEquals(0, gradeProcess.size(), "获取过程考核成绩失败！");
        // gradeProcess.forEach(System.out::println);
    }

    @Test
    void getGradeEndTerm() {
        Map<String, Object> jwzxGradeEndTermList = jwzxGradeService.getGradeEndTerm("2017214032", "067678");
        assertNotNull(jwzxGradeEndTermList, "获取期末成绩失败！");
        // jwzxGradeEndTermList.forEach(System.out::println);
    }

    @Test
    void getGradeMakeUpExam() {
        List<JwzxGradeMakeUpExam> jwzxGradeMakeUpExamList = jwzxGradeService.getGradeMakeUpExam("2017214032", "067678");
        assertNotEquals(0, jwzxGradeMakeUpExamList.size(), "获取补考成绩失败！");
        jwzxGradeMakeUpExamList.forEach(System.out::println);
    }

    @Test
    void getGpa() {
        String gpa = jwzxGradeService.getGpa("2017214032", "067678");

    }

}
