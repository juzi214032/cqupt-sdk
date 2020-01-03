package com.juzi.library.api.impl;

import com.juzi.library.api.LibPersonInfoService;
import com.juzi.library.bean.LibPersonInfo;
import org.junit.jupiter.api.Test;

class LibPersonInfoServiceImplTest extends LibBaseTest {

    private LibPersonInfoService libPersonInfoService = new LibPersonInfoServiceImpl(this.libService);

    @Test
    void getPersonInfo() {
        LibPersonInfo libPersonInfo = libPersonInfoService.getPersonInfo("2017214032", "jqsmx1731815301");
        System.out.println(libPersonInfo);

    }
}
