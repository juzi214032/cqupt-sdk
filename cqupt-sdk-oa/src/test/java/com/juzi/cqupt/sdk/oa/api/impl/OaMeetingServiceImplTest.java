package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaMeetingService;
import com.juzi.cqupt.sdk.oa.bean.OaMeeting;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OaMeetingServiceImplTest extends OaBaseTest {

    private OaMeetingService oaMeetingService = new OaMeetingServiceImpl(this.oaService);

    @Test
    void getMeetingList() {
        List<OaMeeting> newsList = oaMeetingService.getList(1);
        assertNotEquals(0, newsList.size());
    }

    @Test
    void getMeetingDetail() {
        OaMeeting newsDetail = oaMeetingService.getDetail("38563635326344846");
        assertNotNull(newsDetail);
    }

}