package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxTeacherInfoService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxTeacher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JwzxTeacherInfoServiceImplTest extends JwzxBaseTest {

    private JwzxTeacherInfoService jwzxTeacherInfoService = new JwzxTeacherInfoServiceImpl(this.jwzxService);

    @Test
    void getTeacherInfo() {
        List<JwzxTeacher> teacherInfos = jwzxTeacherInfoService.getTeacherInfo("任");
        assertNotEquals(0, teacherInfos.size());
        for (JwzxTeacher teacher : teacherInfos) {
            System.out.println(teacher);
        }
    }
}
