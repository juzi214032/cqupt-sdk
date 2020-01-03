package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxClassroomApplyService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroomApplyInfo;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroomApplyRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@Slf4j
class JwzxClassroomApplyServiceImplTest extends JwzxBaseTest {

    private JwzxClassroomApplyService jwzxClassroomApplyService = new JwzxClassroomApplyServiceImpl(this.jwzxService);

    @Test
    void getApplyInfo() {
        JwzxClassroomApplyInfo applyInfo = jwzxClassroomApplyService.getClassroomApplyInfo(getUsername(), getPassword());
        Assertions.assertNotNull(applyInfo);
        log.debug(applyInfo.toString());
    }

    @Test
    void getAvailableClassroom() {
        List<Map<String, String>> availableClassroom = jwzxClassroomApplyService.getAvailableClassroom(getUsername(), getPassword(), "18", "2", "2");
        Assertions.assertNotEquals(0, availableClassroom.size());
        log.debug(availableClassroom.toString());
    }

    @Test
    void getApplyRecords() {
        List<JwzxClassroomApplyRecord> applyRecords = jwzxClassroomApplyService.getApplyRecords(getUsername(), getPassword());
        Assertions.assertNotEquals(0, applyRecords.size(), "教室申请记录获取失败");
    }

}