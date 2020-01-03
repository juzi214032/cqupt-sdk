package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebHoursService;
import com.juzi.volunteer.web.bean.VolunteerWebHours;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VolunteerWebHoursServiceImplTest extends VolunteerWebBaseTest {

    private VolunteerWebHoursService volunteerWebHoursService = new VolunteerWebHoursServiceImpl(volunteerWebService);

    @Test
    void getHoursInfo() throws IOException {
        List<VolunteerWebHours> volunteerWebHoursList = volunteerWebHoursService.getHoursInfo("140402199904280823", "6611czzlx...", 2);
        assertNotEquals(0, volunteerWebHoursList.size());
        volunteerWebHoursList.forEach(System.out::println);
    }
}
