package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxDate;

import java.time.LocalDate;
import java.util.List;

/**
 * 时间接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/12 12:10
 */
public interface JwzxDateService {

    /**
     * 获取当前周次等时间信息
     *
     * @return 返回当前时间信息
     */
    JwzxDate getNowTime();

    /**
     * 获取指定日期是第几周星期几
     *
     * @param specifyDate 指定日期，格式：yyyy-MM-dd（如2019-09-01）
     * @return
     */
    JwzxDate getTime(String specifyDate);

    /**
     * 获取第几周星期几是几月几日
     *
     * @param week 第几周
     * @param day  星期几
     * @return 日期
     */
    LocalDate getJwzxDate(Integer week, Integer day);

    /**
     * 获取指定日期是第几周星期几
     *
     * @param specifyLocalDate 指定日期
     * @return
     */
    JwzxDate getTime(LocalDate specifyLocalDate);

    /**
     * 获取第一周星期一的年月日
     *
     * @return
     */
    LocalDate getInitTime();

    /**
     * 获取每周每天对应的日期
     * 默认回传30周
     *
     * @return
     */
    List<List<Long>> getWeekDates();
}
