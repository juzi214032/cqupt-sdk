package com.juzi.jwzx.api.impl;

import com.juzi.jwzx.api.JwzxCollegeInfoService;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.bean.college.JwzxCollegeInfo;
import com.juzi.jwzx.bean.college.JwzxMajorInfo;
import com.juzi.jwzx.config.JwzxConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * @author Juzi
 * @date 2019/8/3 15:30
 * Blog https://juzibiji.top
 */
public class JwzxCollegeInfoServiceImpl implements JwzxCollegeInfoService {
    
    private JwzxService jwzxService;
    
    private JwzxConfig jwzxConfig;
    
    public JwzxCollegeInfoServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
        this.jwzxConfig = jwzxService.getConfig();
    }
    
    @Override
    public List<JwzxCollegeInfo> getCollegeInfos() {
        Document document = jwzxService.get(COLLEGE_INFO_URL);
        
        // 所有学院名称
        Elements collegeNameElements = document.getElementById("kbTabs-bj").getElementsByTag("h3");
        // 所有学院信息
        Elements collegeInfoElements = document.getElementById("kbTabs-bj").getElementsByTag("table");
        
        // 遍历每个学院的信息
        List<JwzxCollegeInfo> jwzxCollegeInfoList = new ArrayList<>();
        for (int i = 0; i < collegeNameElements.size(); i++) {
            Element collegeInfoElement = collegeInfoElements.get(i);
            Elements majorInfoElements = collegeInfoElement.getElementsByTag("tbody").get(0).getElementsByTag("tr");
            
            // 遍历该学院内每个专业的信息
            List<JwzxMajorInfo> majorInfoList = new ArrayList<>();
            for (int j = 0; j < majorInfoElements.size(); j++) {
                Element majorInfoElement = majorInfoElements.get(j);
                Elements gradeInfoElements = majorInfoElement.getElementsByTag("td");
                
                // 获取该专业的名称和专业号
                String majorInfos = gradeInfoElements.get(0).text();
                // todo 换正则获取专业号和专业名
                String[] majorInfosArray = majorInfos.split("-");
                String majorId = majorInfosArray[0];
                String majorName = majorInfosArray[1];
                
                // 遍历该专业每个年级的信息
                Map<String, List<String>> clazzMap = new HashMap<>(4);
                for (int k = 1; k < gradeInfoElements.size(); k++) {
                    String grades = gradeInfoElements.get(k).text();
                    // todo 换正则匹配每个班级
                    String[] gradeArray = grades.split(" ");
                    
                    List<String> gradeList = new ArrayList<>(Arrays.asList(gradeArray));
                    clazzMap.put(String.valueOf(5 - k), gradeList);
                }
                JwzxMajorInfo jwzxMajorInfo = new JwzxMajorInfo(majorId, majorName, clazzMap);
                majorInfoList.add(jwzxMajorInfo);
            }
            
            JwzxCollegeInfo jwzxCollegeInfo = new JwzxCollegeInfo(collegeNameElements.get(i).text(), majorInfoList);
            jwzxCollegeInfoList.add(jwzxCollegeInfo);
        }
        return jwzxCollegeInfoList;
    }
}
