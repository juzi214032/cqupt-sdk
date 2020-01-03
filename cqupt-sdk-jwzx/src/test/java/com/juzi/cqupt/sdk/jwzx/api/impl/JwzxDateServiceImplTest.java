package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.alibaba.fastjson.JSON;
import com.juzi.cqupt.sdk.jwzx.api.JwzxDateService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxDate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwzxDateServiceImplTest extends JwzxBaseTest {

    private JwzxDateService jwzxDateService = new JwzxDateServiceImpl(jwzxService);

    @Test
    void getNowTime() {
        JwzxDate nowTime = jwzxDateService.getNowTime();
        assertNotNull(nowTime, "获取教务在线时间失败");
        System.out.println(nowTime);
    }

    @Test
    void getTime() {
        JwzxDate time = jwzxDateService.getTime("2020-01-01");
        assertNotNull(time, "根据指定时间获取教务在线时间失败");
        System.out.println(time);
    }

    @Test
    void getWeekDates() {
        List<List<Long>> weekDates = jwzxDateService.getWeekDates();
        String json = JSON.toJSONString(weekDates);
        System.out.println(json);
    }
}
