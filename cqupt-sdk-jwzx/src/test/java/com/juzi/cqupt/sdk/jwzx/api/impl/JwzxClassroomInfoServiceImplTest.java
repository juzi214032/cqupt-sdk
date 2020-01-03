package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxClassroomInfoService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JwzxClassroomInfoServiceImplTest extends JwzxBaseTest {

    private JwzxClassroomInfoService jwzxClassroomInfoService = new JwzxClassroomInfoServiceImpl(jwzxService);

    @Test
    void getFreeClassroomList() throws IOException {
        List<String> freeClassroomList = jwzxClassroomInfoService.getFreeClassroomList("2", "3", "34");
        assertNotEquals(0, freeClassroomList.size(), "获取空闲教室列表失败");
        System.out.println(freeClassroomList);
    }

    @Test
    void getClassroomList() throws IOException {
        List<JwzxClassroom> classroomList = jwzxClassroomInfoService.getClassroomList();
        assertNotEquals(0, classroomList.size(), "获取教室列表失败");
        classroomList.forEach(System.out::println);
    }
}
