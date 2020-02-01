package com.github.juzi214032.cqupt.sdk.jwzx.api.impl;

import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxExamService;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxCourseExam;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/1 23:30
 */
public class JwzxExamServiceImpl implements JwzxExamService {

    private JwzxService jwzxService;

    public JwzxExamServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<JwzxCourseExam> getCourseExams(String username, String password) {
        List<JwzxCourseExam> courseExams = new ArrayList<>();
        Document document = jwzxService.get(COURSE_EXAM_URL, username, password);
        Elements trs = document.select("#stuKsTab-ks tbody tr");
        for (Element tr : trs) {
            JwzxCourseExam courseExam = new JwzxCourseExam();
            Elements tds = tr.select("td");
            courseExam
                    .setNo(Integer.valueOf(tds.get(0).text()))
                    .setStudentId(tds.get(1).text())
                    .setName(tds.get(2).text())
                    .setExamType(tds.get(3).text())
                    .setCourseId(tds.get(4).text())
                    .setCourseName(tds.get(5).text())
                    .setExamWeekNo(tds.get(6).text())
                    .setExamDay(tds.get(7).text())
                    .setExamTime(tds.get(8).text())
                    .setExamPlace(tds.get(9).text())
                    .setExamSeat(tds.get(10).text())
                    .setExamStatus(tds.get(11).text());
            courseExams.add(courseExam);
        }
        return courseExams;
    }
}
