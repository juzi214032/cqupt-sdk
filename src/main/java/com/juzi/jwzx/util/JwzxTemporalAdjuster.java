package com.juzi.jwzx.util;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * 教务在线时间校正器
 *
 * @author Juzi
 * @date 2019/9/12 12:51
 * Blog https://juzibiji.top
 */
public class JwzxTemporalAdjuster implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate specifyDate = (LocalDate) temporal;
        LocalDate nowDate = LocalDate.now();
        return null;
    }
}
