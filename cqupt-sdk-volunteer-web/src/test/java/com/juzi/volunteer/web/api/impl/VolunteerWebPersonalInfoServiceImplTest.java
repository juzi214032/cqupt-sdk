package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebPersonalInfoService;
import com.juzi.volunteer.web.bean.VolunteerWebPersonalInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class VolunteerWebPersonalInfoServiceImplTest extends VolunteerWebBaseTest {

    private VolunteerWebPersonalInfoService volunteerWebPersonalInfoService = new VolunteerWebPersonalInfoServiceImpl(volunteerWebService);

    @Test
    void getPersonalInfo() throws Exception {
        VolunteerWebPersonalInfo personalInfo = volunteerWebPersonalInfoService.getPersonalInfo("140402199904280823", "6611czzlx...");
        assertNotNull(personalInfo, "获取个人信息失败");
        System.out.println(personalInfo);
    }
}
