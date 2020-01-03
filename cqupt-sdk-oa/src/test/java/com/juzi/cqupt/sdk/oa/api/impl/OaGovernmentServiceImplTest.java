package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaGovernmentService;
import com.juzi.cqupt.sdk.oa.bean.OaGovernment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class OaGovernmentServiceImplTest extends OaBaseTest {

    private OaGovernmentService oaGovernmentService = new OaGovernmentServiceImpl(this.oaService);

    @Test
    void getList() {
        List<OaGovernment> oaGovernmentList = oaGovernmentService.getList(1);
        assertNotEquals(0, oaGovernmentList.size());
    }

    @Test
    void getDetail() {
        OaGovernment detail = oaGovernmentService.getDetail("38563635326342387");
        assertNotNull(detail);
    }

    @Test
    void getTotalPage() {
        int totalPage = oaGovernmentService.getTotalPage();
        assertNotEquals(0, totalPage);
    }
}