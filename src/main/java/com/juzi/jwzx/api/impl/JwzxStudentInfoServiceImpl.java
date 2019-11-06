package com.juzi.jwzx.api.impl;

import com.alibaba.fastjson.JSON;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.api.JwzxStudentInfoService;
import com.juzi.jwzx.bean.student.JwzxStudent;
import com.juzi.jwzx.bean.student.JwzxStudentClass;
import com.juzi.jwzx.bean.student.JwzxStudentExtra;
import com.juzi.jwzx.config.JwzxConfig;
import com.juzi.jwzx.constant.JwzxConstants;
import net.sf.cglib.beans.BeanCopier;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi
 * @date 2019/8/2 12:56
 * Blog https://juzibiji.top
 */
public class JwzxStudentInfoServiceImpl implements JwzxStudentInfoService {
    
    private JwzxService jwzxService;
    
    private JwzxConfig jwzxConfig;
    
    public JwzxStudentInfoServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
        this.jwzxConfig = jwzxService.getConfig();
    }
    
    @Override
    public List<JwzxStudent> getStudetInfo(String keyword) {
        // 发起链接
        String json = jwzxService.get(STUDENT_INFO_URL + keyword).text();
        String studentArrayJson = JSON.parseObject(json).get("returnData").toString();
        List<JwzxStudent> jwzxStudents = JSON.parseArray(studentArrayJson, JwzxStudent.class);
        return jwzxStudents;
    }
    
    @Override
    public JwzxStudentExtra getStudentInfoExtra(String studentId, String password) {
        List<JwzxStudent> studetInfoList = this.getStudetInfo(studentId);
        
        if (studetInfoList.size() == 0) {
            return null;
        }
        
        Document document = jwzxService.get(STUDENT_INFO_EXTRA_URL, studentId, password);
        
        String email = document
                .getElementsByTag("tbody").get(0)
                .getElementsByTag("tr").get(7)
                .getElementsByTag("td").get(1)
                .text();
        
        String unionId = document
                .getElementsByTag("tbody").get(0)
                .getElementsByTag("tr").get(6)
                .getElementsByTag("td").get(1)
                .text();
        
        JwzxStudentExtra studentInfoExtra = new JwzxStudentExtra();
        
        BeanCopier beanCopier = BeanCopier.create(JwzxStudent.class, JwzxStudentExtra.class, false);
        beanCopier.copy(studetInfoList.get(0), studentInfoExtra, null);
        
        studentInfoExtra.setEmail(email).setUnionId(unionId);
        return studentInfoExtra;
    }
    
    @Override
    public List<JwzxStudentClass> getClassStudentList(String classId) {
        List<JwzxStudentClass> classStudentList = new ArrayList<>();
        Document document = jwzxService.get(CLASS_STUDENT_URL + classId);
        Elements trs = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (Element tr : trs) {
            JwzxStudentClass student = new JwzxStudentClass();
            Elements tds = tr.getElementsByTag("td");
            student
                    .setStudentId(tds.get(1).text())
                    .setName(tds.get(2).text())
                    .setGender(tds.get(3).text())
                    .setClazz(tds.get(4).text())
                    .setMajorId(tds.get(5).text())
                    .setMajorName(tds.get(6).text())
                    .setCollegeName(tds.get(7).text())
                    .setGrade(tds.get(8).text())
                    .setStudyStatus(tds.get(9).text());
            student.setSelectCourseStatus(tds.get(10).text());
            classStudentList.add(student);
        }
        return classStudentList;
    }
    
    @Override
    public byte[] getStudentPhoto(String studentId) {
        String username = jwzxConfig.getUsername();
        String password = jwzxConfig.getPassword();
        
        if (username == null || password == null) {
            throw new RuntimeException("未设置账号密码，不能抓取学生照片");
        }
        
        String cookie = jwzxService.login(username, password);
        Connection.Response execute = null;
        try {
            execute = Jsoup
                    .connect(jwzxConfig.getDomain() + STUDENT_PHOTO_URL + studentId)
                    .cookie(JwzxConstants.COOKIE_NAME, cookie)
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert execute != null;
        return execute.bodyAsBytes();
    }
}
