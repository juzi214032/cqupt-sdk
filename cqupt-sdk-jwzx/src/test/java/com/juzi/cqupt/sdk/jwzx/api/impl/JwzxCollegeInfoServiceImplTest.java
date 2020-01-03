package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxCollegeInfoService;
import com.juzi.cqupt.sdk.jwzx.bean.college.JwzxCollegeInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwzxCollegeInfoServiceImplTest extends JwzxBaseTest {

    private JwzxCollegeInfoService jwzxCollegeInfoService = new JwzxCollegeInfoServiceImpl(jwzxService);

    @Test
    void getCollegeInfos() {
        List<JwzxCollegeInfo> collegeInfos = jwzxCollegeInfoService.getCollegeInfos();
        assertNotEquals(0, collegeInfos.size());
        for (JwzxCollegeInfo college : collegeInfos) {
            System.out.println(college);
        }
    }
}
