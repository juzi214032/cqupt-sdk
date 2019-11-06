package com.juzi.jwzx.api.impl;

import com.juzi.jwzx.api.JwzxDateService;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.bean.JwzxDate;
import com.juzi.jwzx.util.RegexUtil;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Juzi
 * @date 2019/9/12 12:13
 * Blog https://juzibiji.top
 */
public class JwzxDateServiceImpl implements JwzxDateService {
    
    private final Pattern TIME_REGEX = Pattern.compile("(.{9})学年(\\d{1})学期 第 (\\d{1,2}) 周 星期 (.{1})");
    private JwzxService jwzxService;
    
    public JwzxDateServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }
    
    @Override
    public JwzxDate getNowTime() {
        JwzxDate jwzxDate = new JwzxDate();
        
        Document document = jwzxService.get("");
        List<String> dataList = RegexUtil.parse(TIME_REGEX, document.text());
        
        jwzxDate
                .setStudyYear(dataList.get(0))
                .setTerm(dataList.get(1))
                .setWeek(Integer.parseInt(dataList.get(2)))
                .setDay(Integer.valueOf(dataList.get(3)));
        
        return jwzxDate;
    }
    
    @Override
    public JwzxDate getTime(String specifyDate) {
        // 定义时间格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        // 传入的时间
        LocalDate specifyLocalDate = LocalDate.parse(specifyDate, dateFormatter);
        
        return this.getTime(specifyLocalDate);
    }
    
    @Override
    public JwzxDate getTime(LocalDate specifyLocalDate) {
        
        // 现在的时间
        LocalDate nowLocalDate = LocalDate.now();
        
        // 计算两个时间相差的天数
        long dayPeriod = ChronoUnit.DAYS.between(nowLocalDate, specifyLocalDate);
        
        // 获取现在的教务在线时间
        JwzxDate nowJwzxDate = this.getNowTime();
        
        // 相差周数
        int weeks = (int) ((nowJwzxDate.getDay() + dayPeriod) / 7);
        // 星期几
        int days = specifyLocalDate.getDayOfWeek().getValue();
        if (specifyLocalDate.compareTo(nowLocalDate) > 0) {
            // 如果指定时间是未来，则周数相加
            int newWeek = nowJwzxDate.getWeek() + weeks;
            // 周数超出范围
            if (newWeek > 30) {
                throw new RuntimeException("指定日期超出本学期正常范围");
            }
            nowJwzxDate
                    .setWeek(newWeek)
                    .setDay(days);
        } else if (specifyLocalDate.compareTo(nowLocalDate) < 0) {
            // 如果指定时间是过去，则周数相减
            int newWeek = nowJwzxDate.getWeek() + weeks;
            // 周数超出范围
            if (newWeek <= 0) {
                throw new RuntimeException("指定日期超出本学期正常范围");
            }
            nowJwzxDate
                    .setWeek(newWeek)
                    .setDay(days);
        }
        
        
        return nowJwzxDate;
    }
    
    @Override
    public LocalDate getInitTime() {
        JwzxDate nowJwzxTime = this.getNowTime();
        // 今天距离第一周第1天的天数
        int dayNums = (nowJwzxTime.getWeek() - 1) * 7 + nowJwzxTime.getDay();
        
        LocalDate nowLocalDate = LocalDate.now();
        LocalDate initLocalDate = nowLocalDate.minusDays(dayNums - 1);
        
        return initLocalDate;
    }
    
    @Override
    public List<List<Long>> getWeekDates() {
        LocalDate initTime = this.getInitTime();
        
        List<List<Long>> weekDateList = new ArrayList<>();
        
        for (int i = 0; i < 30; i++) {
            List<Long> localDateList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                localDateList.add(initTime.atStartOfDay(ZoneId.of("+8")).toInstant().toEpochMilli());
                initTime = initTime.plusDays(1);
            }
            weekDateList.add(localDateList);
        }
        
        return weekDateList;
    }
}
