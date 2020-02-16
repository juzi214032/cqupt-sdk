package com.github.juzi214032.cqupt.sdk.jwzx.api.impl;

import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxClassroomInfoService;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxService;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxClassroom;
import com.github.juzi214032.cqupt.sdk.common.util.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/9 20:48
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
    public List<JwzxClassroom> getFreeClassroomList(String zc, String xq, String[] sd) {
        List<JwzxClassroom> classroomList = new ArrayList<>();
        // 构造参数
        Map<String, String> data = new HashMap<>(sd.length + 3);
        data.put("zcStart", zc);
        data.put("zcEnd", zc);
        data.put("xq", xq);
        for (String value : sd) {
            data.put("sd[]", value);
        }

        Document document = jwzxService.get(FREE_CLASSROOM_API, data);
        List<String> classroomStringList = document.select("a").eachText();
        for (String value : classroomStringList) {
            List<String> results = RegexUtil.parse(FREE_CLASSROOM_REGEX, value);
            // 防止没获取到教室容纳人数造成空指针异常
            if(results.size()==1){
                results.add("0");
            }
            JwzxClassroom jwzxClassroom = new JwzxClassroom();
            jwzxClassroom.setName(results.get(0)).setContain(results.get(1));
            classroomList.add(jwzxClassroom);
        }
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
