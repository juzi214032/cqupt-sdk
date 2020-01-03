package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxClassroomInfoService;
import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Juzi
 * @since 2019/9/9 20:48
 * Blog https://juzibiji.top
 */
public class JwzxClassroomInfoServiceImpl implements JwzxClassroomInfoService {

    private JwzxService jwzxService;

    public JwzxClassroomInfoServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<String> getFreeClassroomList(String zc, String xq, String sd) {

        Map<String, String> data = new HashMap<>(3);
        data.put("zc", zc);
        data.put("xq", xq);
        data.put("sd", sd);

        Document document = jwzxService.get(FREE_CLASSROOM_URL, data);

        Elements tds = document.getElementsByTag("tbody").get(0).getElementsByTag("input");

        List<String> classroomList = tds.stream()
                .map(e -> e.attr("value"))
                .collect(Collectors.toList());

        return classroomList;
    }

    @Override
    public List<JwzxClassroom> getClassroomList() {
        List<JwzxClassroom> jwzxClassroomList = new ArrayList<>();
        Document document = jwzxService.get(CLASSROOM_INFO_URL);

        Elements classroomTypeElements = document.getElementById("kbTabs-room").getElementsByTag("h3");
        Elements classroomInfoElements = document.getElementById("kbTabs-room").getElementsByTag("tbody");

        for (int i = 0; i < classroomInfoElements.size(); i++) {
            Element classroomInfoElement = classroomInfoElements.get(i);
            Elements classroomElements = classroomInfoElement.getElementsByTag("td");

            String classroomOfCollege = null;

            for (int j = 0; j < classroomElements.size(); j++) {

                JwzxClassroom jwzxClassroom = new JwzxClassroom();

                if (!"".equals(classroomElements.get(j).attr("rowspan"))) {
                    // 教室所属学院
                    classroomOfCollege = classroomElements.get(j).text();
                    continue;
                }

                // 教室名称
                String classroomName = classroomElements.get(j)
                        .getElementsByTag("a").get(0)
                        .ownText();

                String classroomContain = null;
                if (classroomOfCollege == null) {
                    // 教室容量（人数）
                    classroomContain = classroomElements.get(j)
                            .getElementsByTag("a").get(0)
                            .getElementsByTag("span").get(0)
                            .ownText()
                            .replaceAll("[()]", "");
                }

                jwzxClassroom
                        .setName(classroomName)
                        .setContain(classroomContain)
                        .setCollege(classroomOfCollege)
                        .setType(classroomTypeElements.get(i).text());

                jwzxClassroomList.add(jwzxClassroom);

            }
        }
        return jwzxClassroomList;
    }
}
