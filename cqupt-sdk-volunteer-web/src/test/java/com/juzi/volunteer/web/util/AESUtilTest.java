package com.juzi.volunteer.web.util;

import com.juzi.volunteer.web.constant.VolunteerWebConstant;
import com.juzi.volunteer.web.util.encrypt.RSAUtil;
import org.junit.jupiter.api.Test;

class AESUtilTest {

    @Test
    void encrypt() throws Exception {
        String str = RSAUtil.encrypt("eUU5VWfV88nPXib4", VolunteerWebConstant.PUBLIC_KEY);
        System.out.println(str);

    }
}
