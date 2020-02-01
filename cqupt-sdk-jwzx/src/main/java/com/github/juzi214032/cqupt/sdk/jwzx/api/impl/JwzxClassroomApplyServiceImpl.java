package com.github.juzi214032.cqupt.sdk.jwzx.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxClassroomApplyService;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxService;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxClassroom;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxClassroomApplyInfo;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxClassroomApplyRecord;
import com.github.juzi214032.cqupt.sdk.jwzx.exception.JwzxClassroomApplyException;
import com.github.juzi214032.cqupt.sdk.jwzx.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/3 16:50
 */
@Slf4j
public class JwzxClassroomApplyServiceImpl implements JwzxClassroomApplyService {

    private JwzxService jwzxService;

    public JwzxClassroomApplyServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public JwzxClassroomApplyInfo getClassroomApplyInfo(String username, String password) {
        log.debug("用户[{}]，开始获取申请教室固定信息", username);
        Document document = this.jwzxService.get(CREATE_APPLY_URL, username, password);
        // 申请类型
        Elements typeElements = document.select("select[name=reason] option");
        // 可选周次
        Elements weekElements = document.select("select[name=zc] option");
        // 可选星期几
        Elements weekNoElements = document.select("select[name=xq] option");
        // 可选第几节
        Elements courseNoElements = document.select("select[name=sd] option");

        // 获取申请类型
        List<Map<String, String>> types = new ArrayList<>();
        for (Element element : typeElements) {
            Map<String, String> type = new HashMap<>(1);
            type.put(element.text(), element.attr("value"));
            types.add(type);
        }

        // 获取可选周次
        List<Map<String, String>> weeks = new ArrayList<>();
        for (Element element : weekElements) {
            Map<String, String> week = new HashMap<>(1);
            week.put(element.text(), element.attr("value"));
            weeks.add(week);
        }

        // 获取可选星期几
        List<Map<String, String>> weekNos = new ArrayList<>();
        for (Element element : weekNoElements) {
            Map<String, String> weekNo = new HashMap<>(1);
            weekNo.put(element.text(), element.attr("value"));
            weekNos.add(weekNo);
        }

        // 获取可选第几节
        List<Map<String, String>> courseNos = new ArrayList<>();
        for (Element element : courseNoElements) {
            Map<String, String> courseNo = new HashMap<>(1);
            courseNo.put(element.text(), element.attr("value"));
            courseNos.add(courseNo);
        }

        JwzxClassroomApplyInfo jwzxClassroomApplyInfo = new JwzxClassroomApplyInfo();
        log.debug("用户[{}]，结束获取申请教室固定信息", username);
        return jwzxClassroomApplyInfo
                .setAllTypes(types)
                .setWeek(weeks)
                .setWeekNo(weekNos)
                .setCourseNo(courseNos);
    }

    @Override
    public List<JwzxClassroom> getAvailableClassroom(String username, String password, String week, String weekNo, String courseNo) {
        log.debug("用户[{}]，开始获取可用教室", username);

        List<JwzxClassroom> classroomList = new ArrayList<>();

        // 构造请求参数
        Map<String, String> param = new HashMap<>(3);
        param.put("zc", week);
        param.put("xq", weekNo);
        param.put("sd", courseNo);

        Document document = this.jwzxService.get(AVAILABLE_CLASSROOM_URL, username, password, param);

        // 获取可申请教室列表
        List<String> classroomStringList = document.select("td").eachText();

        for (String value : classroomStringList) {
            List<String> results = RegexUtil.parse(AVAILABLE_CLASSROOM_PATTERN, value);
            JwzxClassroom classroom = new JwzxClassroom();
            classroom.setName(results.get(0)).setContain(results.get(1));
            classroomList.add(classroom);
        }
        log.debug("用户[{}]，结束获取可用教室", username);
        return classroomList;
    }

    @Override
    public List<JwzxClassroomApplyRecord> getApplyRecords(String username, String password) {
        log.debug("用户[{}]，开始获取教室申请记录", username);
        List<JwzxClassroomApplyRecord> applyRecords = new ArrayList<>();
        Document document = this.jwzxService.get(APPLY_CLASSROOM_RECORD_URL, username, password);

        // 获取申请记录信息
        Elements recordElements = document.select("tbody tr");
        for (Element element : recordElements) {
            Elements resultElements = element.select("td");
            JwzxClassroomApplyRecord record = new JwzxClassroomApplyRecord();
            record
                    .setTerm(resultElements.get(1).text())
                    .setApplyTime(resultElements.get(2).text())
                    .setBorrowTime(resultElements.get(3).text())
                    .setTitle(resultElements.get(4).text())
                    .setPeopleCount(resultElements.get(5).text())
                    .setLeader(resultElements.get(6).text())
                    .setClassroom(resultElements.get(7).text())
                    .setResult(resultElements.get(8).text());
            applyRecords.add(record);
        }
        log.debug("用户[{}]，结束获取教室申请记录", username);
        return applyRecords;
    }

    @Override
    public boolean postClassroomAppply(String username, String password, String type, String title, String college, String leader, String name, String phone, String week, String weekNo, String courseNo, String classroom) throws JwzxClassroomApplyException {
        log.debug("用户[{}]，开始提交教室申请", username);
        // 构造请求参数
        Map<String, String> param = new HashMap<>(10);
        param.put("action", "jssq");
        param.put("reason", type);
        param.put("content", title);
        param.put("dw", college);
        param.put("fzr", leader);
        param.put("tel", name + "-" + phone);
        param.put("zc", week);
        param.put("xq", weekNo);
        param.put("sd", courseNo);
        param.put("room", classroom);

        Document document = this.jwzxService.post(APPLY_CLASSROOM_API, username, password, param);
        JSONObject resultJson = JSONObject.parseObject(document.text());

        log.debug("用户[{}]，结束提交教室申请", username);
        if ("0".equals(resultJson.get("code").toString())) {
            // 提交成功
            return true;
        } else {
            String tips = resultJson.get("info").toString();
            log.warn("用户[{}]，提交教室申请异常，教务在线返回信息[{}]", username, tips);
            throw new JwzxClassroomApplyException(tips);
        }
    }
}
