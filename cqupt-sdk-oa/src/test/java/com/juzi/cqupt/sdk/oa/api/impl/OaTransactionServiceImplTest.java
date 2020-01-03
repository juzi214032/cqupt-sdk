package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaTransactionService;
import com.juzi.cqupt.sdk.oa.bean.OaTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class OaTransactionServiceImplTest extends OaBaseTest {

    private OaTransactionService oaTransactionService = new OaTransactionServiceImpl(this.oaService);

    @Test
    void getList() {
        List<OaTransaction> oaTransactionServiceList = oaTransactionService.getList(1);
        assertNotEquals(0, oaTransactionServiceList.size());
    }

    @Test
    void getDetail() {
        OaTransaction transaction = oaTransactionService.getDetail("38563635326343571");
        assertNotNull(transaction);
    }

    @Test
    void getTotalPage() {
        int totalPage = oaTransactionService.getTotalPage();
        assertNotEquals(0, totalPage);
        log.debug("事务通知的一共有[{}]页", totalPage);
    }
}