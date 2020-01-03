package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.api.JwzxTimetableService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxTimetable;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi
 * @since 2019/9/6 17:31
 * Blog https://juzibiji.top
 */
public class JwzxTimetableServiceImpl implements JwzxTimetableService {

    private JwzxService jwzxService;

    public JwzxTimetableServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<JwzxTimetable> getStudentTimetable(String studentId) {
        Document document = jwzxService.get(STUDENT_COURSE_TABLE_URL + studentId);
        Element tbody = document.getElementById("kbStuTabs-table").getElementsByTag("tbody").get(0);
        return this.parseCourseTable(tbody);
    }

    @Override
    public List<JwzxTimetable> getTeacherTimetable(String teacherId) {
        Document document = jwzxService.get(TEACHER_COURSE_TABLE_URL + teacherId);
        Element tbody = document.getElementById("kbTeaTabs-table").getElementsByTag("tbody").get(0);
        return this.parseCourseTable(tbody);
    }

    /**
     * 解析课程是几节连上
     *
     * @param elements
     * @return
     */
    private Integer parseDurationInfo(Elements elements) {
        if (elements == null || elements.size() == 0) {
            return 2;
        }

        if (StringUtils.isBlank(elements.get(0).text())) {
            return 2;
        }

        String duration = elements.get(0).text().substring(0, 1);

        return Integer.valueOf(duration);
    }

    private List<JwzxTimetable> parseCourseTable(Element tbody) {
        List<JwzxTimetable> courseTableList = new ArrayList<>();
        // 课表所有行
        Elements trs = tbody.getElementsByTag("tr");

        int courseStartNo = 1;
        for (int i = 0; i < trs.size(); i++) {
            // 跳过午间休息和晚间休息
            if (i == 2 || i == 5) {
                continue;
            }
            // 课表一行中的所有单元格
            Elements tds = trs.get(i).getElementsByTag("td");
            for (int j = 1; j < tds.size(); j++) {
                // 每个单元格中所有的课
                Elements kbs = tds.get(j).getElementsByClass("kbTd");
                for (Element kb : kbs) {
                    List<TextNode> kbBaseInfo = kb.textNodes();

                    JwzxTimetable jwzxTimetable = new JwzxTimetable();
                    String[] kbExtraInfo = kb.getElementsByAttributeValue("style", "color:#0000FF").get(0).text().split(" ");

                    // 处理学分
                    String credit = "";
                    if (kbExtraInfo.length > 2) {
                        credit = kbExtraInfo[2].replaceAll("学分", "");
                        if (credit.length() == 2) {
                            credit = "0" + credit;
                        }
                    }

                    jwzxTimetable
                            .setClassId(kbBaseInfo.get(0).toString().trim())
                            .setCourseId(kbBaseInfo.get(1).toString().split("-", 2)[0].trim())
                            .setCourseName(kbBaseInfo.get(1).toString().split("-", 2)[1].trim())
                            .setPlace(kbBaseInfo.get(2).toString().replaceAll("地点：", "").trim())
                            .setWeekView(kbBaseInfo.get(3).toString().trim())
                            .setTeacherName(kbExtraInfo[0])
                            .setType(kbExtraInfo[1])
                            .setCredits(credit)
                            .setDuration(parseDurationInfo(kb.getElementsByAttributeValue("color", "#FF0000")))
                            .setWeekBin(kb.attr("zc").trim())
                            .setCourseStartNo(courseStartNo)
                            .setCourseWeek(j);
                    // 存入集合
                    courseTableList.add(jwzxTimetable);
                }
            }
            courseStartNo += 2;
        }
        return courseTableList;
    }
}
