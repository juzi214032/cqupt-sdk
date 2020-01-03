package com.juzi.library.api.impl;

import org.junit.jupiter.api.Test;

import java.util.Map;

class LibServiceImplTest extends LibBaseTest {

    @Test
    void login() {
        Map<String, String> cookies = this.libService.login("2017214032", "2017214032", "鞠青松", false);
        System.out.println(cookies);
    }
}
