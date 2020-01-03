package com.juzi.cqupt.sdk.common.util;

import com.juzi.cqupt.sdk.common.enums.CASService;
import com.juzi.cqupt.sdk.common.exception.CASLoginFailedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
class CASLoginUtilTest {

    @Test
    void jwzxLogin() throws CASLoginFailedException {

        Map<String, String> cookies = CASLoginUtil.login("1655633", "jqsmx1731815301", CASService.JWZX);
        assertNotEquals(0, cookies.size());
        log.debug("教务在线登录获取的cookie是[{}]", cookies);
    }

    @Test
    void libraryLogin() throws CASLoginFailedException {
        Map<String, String> cookies = CASLoginUtil.login("1655633", "jqsmx1731815301", CASService.LIBRARY);
        assertNotEquals(0, cookies.size());
        log.debug("图书馆登录获取的cookie是[{}]", cookies);
    }
}