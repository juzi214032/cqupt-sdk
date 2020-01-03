package com.juzi.cqupt.sdk.jwzx.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
class JwzxServiceImplTest extends JwzxBaseTest {
    @Test
    void loginByDefault() {
        String cookie = jwzxService.login("2017214032", "067678");
        assertNotEquals(0, cookie.length());
        log.debug("教务在线网站登录获取到的cookie是[{}]", cookie);
    }

    @Test
    void loginByCAS() {
        String cookie = jwzxService.login("1655633", "jqsmx1731815301");
        assertNotEquals(0, cookie.length());
        log.debug("教务在线统一身份认证登录获取到的cookie是[{}]", cookie);
    }
}
