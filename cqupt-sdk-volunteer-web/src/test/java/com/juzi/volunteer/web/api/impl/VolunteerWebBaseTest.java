package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebService;
import com.juzi.volunteer.web.config.VolunteerWebConfig;
import com.juzi.volunteer.web.config.VolunteerWebSimpleConfig;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Juzi
 * @since 2019/10/13 17:33
 * Blog https://juzibiji.top
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VolunteerWebBaseTest {

    protected VolunteerWebConfig volunteerWebConfig = new VolunteerWebSimpleConfig();

    protected VolunteerWebService volunteerWebService = new VolunteerWebServiceImpl(volunteerWebConfig);

}
