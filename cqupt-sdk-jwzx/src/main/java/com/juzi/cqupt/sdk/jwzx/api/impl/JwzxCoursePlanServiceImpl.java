package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxCoursePlanService;
import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxCoursePlan;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/9 10:29
 */
public class JwzxCoursePlanServiceImpl implements JwzxCoursePlanService {

    private JwzxService jwzxService;

    public JwzxCoursePlanServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<JwzxCoursePlan> getCoursePlans(String username, String password) {
        List<JwzxCoursePlan> coursePlanList = new ArrayList<>();
        Document document = jwzxService.get(COURSE_PLAN_URL, username, password);
        Elements coursePlanElements = document.select("tbody tr");
        for (Element element : coursePlanElements) {
            Elements result = element.select("td");
            JwzxCoursePlan coursePlan = new JwzxCoursePlan();
            coursePlan
                    .setClassId(result.get(0).text())
                    .setCourseId(result.get(1).text())
                    .setCourseName(result.get(2).text())
                    .setCourseType(result.get(3).text())
                    .setCourseSort(result.get(4).text())
                    .setGradeComposition(result.get(0).text());
            coursePlanList.add(coursePlan);
        }
        return coursePlanList;
    }
}
