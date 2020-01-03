package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebService;
import com.juzi.volunteer.web.config.VolunteerWebConfig;
import com.juzi.volunteer.web.config.VolunteerWebSimpleConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VolunteerWebServiceImplTest {

    private VolunteerWebConfig volunteerWebConfig;

    private VolunteerWebService volunteerWebService;

    @BeforeAll
    void befor() {
        this.volunteerWebConfig = new VolunteerWebSimpleConfig();
        this.volunteerWebService = new VolunteerWebServiceImpl(this.volunteerWebConfig);
    }

    @Test
    void login() throws Exception {
        volunteerWebService.login("cqupt2017214032", "eUU5VWfV88nPXib4");
    }
}
