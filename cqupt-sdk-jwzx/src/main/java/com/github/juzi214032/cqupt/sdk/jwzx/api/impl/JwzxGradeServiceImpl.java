package com.github.juzi214032.cqupt.sdk.jwzx.api.impl;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeEndTerm;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeMakeUpExam;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeProcess;
import com.github.juzi214032.cqupt.sdk.jwzx.bean.grade.JwzxGradeTotal;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxGradeService;
import com.github.juzi214032.cqupt.sdk.jwzx.api.JwzxService;
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
 * @since 2019/9/11 12:31
 */
public class JwzxGradeServiceImpl implements JwzxGradeService {

    private JwzxService jwzxService;

    public JwzxGradeServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<JwzxGradeTotal> getGradeTotal(String username, String password) {
        return getGradeTotal(username, password, null);
    }

    @Override
    public List<JwzxGradeTotal> getGradeTotal(String username, String password, String creditType) {

        Document document = jwzxService.get(GRADE_TOTAL_URL, username, password);

        Elements trs;

        // 获取不同类型学分总表
        if ("A".equals(creditType)) {
            // A学分
            trs = document.getElementById("bzyTable").getElementsByTag("tbody").get(0).getElementsByTag("tr");
        } else if ("B".equals(creditType)) {
            // B学分
            trs = document.getElementById("bxfTable").getElementsByTag("tbody").get(0).getElementsByTag("tr");
        } else {
            // A学分和B学分
            Elements axfTable = document.getElementById("bzyTable").getElementsByTag("tbody").get(0).getElementsByTag("tr");
            Elements bxfTable = document.getElementById("bxfTable").getElementsByTag("tbody").get(0).getElementsByTag("tr");
            axfTable.addAll(bxfTable);
            trs = axfTable;
        }

        // 成绩列表
        List<JwzxGradeTotal> gradeTotalList = new ArrayList<>();
        for (Element element : trs) {
            Elements tds = element.getElementsByTag("td");
            JwzxGradeTotal jwzxGradeTotal = new JwzxGradeTotal();

            // 判断选修课类型（人文/自然）
            String electiveCourseType = "";
            if (!tds.get(10).text().isEmpty()) {
                electiveCourseType = "人文";
            } else if (!tds.get(11).text().isEmpty()) {
                electiveCourseType = "自然";
            }

            // 判断是否统计学分
            boolean isCountGradePoint = "checked".equals(tds.get(15).getElementsByTag("input").get(0)
                    .attr("checked"));

            jwzxGradeTotal
                    .setTerm(tds.get(1).text())
                    .setCourseId(tds.get(2).text())
                    .setCourseName(tds.get(3).text())
                    .setType(tds.get(4).text())
                    .setCredit(tds.get(5).text())
                    .setExamNature(tds.get(6).text())
                    .setGrade(tds.get(7).text())
                    .setGradePoint(tds.get(8).text())
                    .setExperimentGrade(tds.get(9).text())
                    .setElectiveCourseType(electiveCourseType)
                    .setIsCrossMajor(!tds.get(12).text().isEmpty())
                    .setIsCountGradePoint(!tds.get(13).text().isEmpty())
                    .setRemark(tds.get(14).text())
                    .setIsCountGradePoint(isCountGradePoint);

            gradeTotalList.add(jwzxGradeTotal);
        }

        return gradeTotalList;
    }

    @Override
    public Map<String, Object> getGradeProcess(String username, String password) {
        List<JwzxGradeProcess> jwzxGradeProcessList = new ArrayList<>();

        // 获取网页
        Document document = jwzxService.get(GRADE_PROCESS_URL, username, password);

        // 筛选出表格中的每一行
        Elements trs = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        int rowHeight = 0;
        JwzxGradeProcess lastJwzxGradeProcess = new JwzxGradeProcess();
        // 遍历每行
        for (Element tr : trs) {
            JwzxGradeProcess jwzxGradeProcess = lastJwzxGradeProcess;
            Elements tds = tr.getElementsByTag("td");

            // 根据rowspan属性判断当前课程是否有多个分项成绩
            if (rowHeight <= 0) {
                jwzxGradeProcess = new JwzxGradeProcess();
                List<JwzxGradeProcess.JwzxGradeProcessDetail> gradeProcessDetailList = new ArrayList<>();
                JwzxGradeProcess.JwzxGradeProcessDetail gradeProcessDetail = new JwzxGradeProcess().new JwzxGradeProcessDetail();
                String rowspan = tds.get(0).attr("rowspan");
                rowHeight = Integer.parseInt("".equals(rowspan) ? "0" : rowspan);

                // 课程单项成绩（可能会有多个）
                gradeProcessDetail
                        .setTeacher(tds.get(4).text())
                        .setExaminTitle(tds.get(5).text())
                        .setExaminType(tds.get(6).text())
                        .setGrade(tds.get(7).text())
                        .setGradeRatio(tds.get(8).text());
                gradeProcessDetailList.add(gradeProcessDetail);

                // 课程基本信息
                jwzxGradeProcess
                        .setTerm(tds.get(0).text().trim())
                        .setCourseId(tds.get(1).text().trim())
                        .setCourseName(tds.get(2).text().trim())
                        .setClassId(tds.get(3).text().trim())
                        .setGradeDetails(gradeProcessDetailList);
                jwzxGradeProcessList.add(jwzxGradeProcess);
            } else {
                // 获取上一个课程的分项成绩
                JwzxGradeProcess.JwzxGradeProcessDetail gradeProcessDetail = new JwzxGradeProcess().new JwzxGradeProcessDetail();
                gradeProcessDetail
                        .setTeacher(tds.get(0).text().trim())
                        .setExaminTitle(tds.get(1).text().trim())
                        .setExaminType(tds.get(2).text().trim())
                        .setGrade(tds.get(3).text().trim())
                        .setGradeRatio(tds.get(4).text().trim());
                jwzxGradeProcess.getGradeDetails().add(gradeProcessDetail);
            }
            rowHeight--;
            lastJwzxGradeProcess = jwzxGradeProcess;
        }

        String date = document.getElementsByTag("a").get(1).text();

        Map<String, Object> result = new HashMap<>(2);
        result.put("date", date);
        result.put("grade", jwzxGradeProcessList);

        return result;
    }

    @Override
    public Map<String, Object> getGradeEndTerm(String username, String password) {
        List<JwzxGradeEndTerm> jwzxGradeEndTermList = new ArrayList<>();
        Document document = this.jwzxService.get(GRADE_END_TERM_URL, username, password);
        Elements trs = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            JwzxGradeEndTerm jwzxGradeEndTerm = new JwzxGradeEndTerm();
            jwzxGradeEndTerm
                    .setTerm(tds.get(0).text())
                    .setCourseId(tds.get(1).text())
                    .setCourseName(tds.get(2).text())
                    .setClassId(tds.get(3).text())
                    .setCourseType(tds.get(4).text())
                    .setStudyStatus(tds.get(5).text())
                    .setProcessGrade(tds.get(6).text())
                    .setTestNature(tds.get(7).text())
                    .setEndTermGrade(tds.get(8).text())
                    .setEndTermGradeRatio(tds.get(9).text())
                    .setFinalGrade(tds.get(10).text());
            jwzxGradeEndTermList.add(jwzxGradeEndTerm);
        }

        // 获取网页
        Document tempDocument = jwzxService.get(GRADE_PROCESS_URL, username, password);
        String date = tempDocument.getElementsByTag("a").get(2).text();

        Map<String, Object> result = new HashMap<>(2);
        result.put("date", date);
        result.put("grade", jwzxGradeEndTermList);

        return result;
    }

    @Override
    public List<JwzxGradeMakeUpExam> getGradeMakeUpExam(String username, String password) {
        List<JwzxGradeMakeUpExam> jwzxGradeMakeUpExamList = new ArrayList<>();
        Document document = this.jwzxService.get(GRADE_MAKE_UP_EXAM_URL, username, password);
        Elements trs = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            JwzxGradeMakeUpExam jwzxGradeMakeUpExam = new JwzxGradeMakeUpExam();
            jwzxGradeMakeUpExam
                    .setTerm(tds.get(0).text())
                    .setCourseId(tds.get(1).text())
                    .setCourseName(tds.get(2).text())
                    .setClassId(tds.get(3).text())
                    .setCourseType(tds.get(4).text())
                    .setFinalGrade(tds.get(10).text());
            jwzxGradeMakeUpExamList.add(jwzxGradeMakeUpExam);
        }
        return jwzxGradeMakeUpExamList;
    }

    @Override
    public String getGpa(String username, String password) {
        Document document = jwzxService.get(GRADE_TOTAL_URL, username, password);
        List<Element> trs = document
                .getElementById("cjAllTab-zypm")
                .getElementsByTag("tr")
                .stream()
                .filter(x -> "平均成绩绩点".equals(x.getElementsByTag("td").get(1).text()))
                .collect(Collectors.toList());

        Element tr = trs.get(trs.size() - 1);
        String gradePoint = tr.getElementsByTag("td").get(2).text();
        return gradePoint;
    }
}
