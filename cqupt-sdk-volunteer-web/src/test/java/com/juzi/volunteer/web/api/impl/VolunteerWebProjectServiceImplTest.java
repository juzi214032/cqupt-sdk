package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebProjectService;
import com.juzi.volunteer.web.api.VolunteerWebService;
import com.juzi.volunteer.web.bean.VolunteerWebPageInfo;
import com.juzi.volunteer.web.bean.VolunteerWebProject;
import com.juzi.volunteer.web.config.VolunteerWebConfig;
import com.juzi.volunteer.web.config.VolunteerWebSimpleConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VolunteerWebProjectServiceImplTest {

    private VolunteerWebProjectService volunteerWebProjectService;

    @BeforeAll
    void befor() {
        VolunteerWebConfig volunteerWebConfig = new VolunteerWebSimpleConfig();
        VolunteerWebService volunteerWebService = new VolunteerWebServiceImpl(volunteerWebConfig);
        volunteerWebProjectService = new VolunteerWebProjectServiceImpl(volunteerWebService);
    }

    @Test
    void getMyProject() throws Exception {
        List<VolunteerWebProject> myProject = volunteerWebProjectService.getMyProject("140402199904280823", "6611czzlx...", 2);
        assertNotEquals(0, myProject.size());
        System.out.println(myProject.toString());
    }

    @Test
    void getPageInfoTest() throws IOException {
        VolunteerWebPageInfo pageInfo = volunteerWebProjectService.getPageInfo("140402199904280823", "6611czzlx...");
        assertNotNull(pageInfo);
        System.out.println(pageInfo.toString());
    }
}
