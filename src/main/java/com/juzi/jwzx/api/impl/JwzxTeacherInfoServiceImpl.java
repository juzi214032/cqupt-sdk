package com.juzi.jwzx.api.impl;

import com.alibaba.fastjson.JSON;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.api.JwzxTeacherInfoService;
import com.juzi.jwzx.bean.JwzxTeacher;
import com.juzi.jwzx.config.JwzxConfig;

import java.util.List;

/**
 * @author Juzi
 * @date 2019/8/3 14:49
 * Blog https://juzibiji.top
 */
public class JwzxTeacherInfoServiceImpl implements JwzxTeacherInfoService {
    
    private JwzxService jwzxService;
    
    private JwzxConfig jwzxConfig;
    
    public JwzxTeacherInfoServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
        this.jwzxConfig = jwzxService.getConfig();
    }
    
    @Override
    public List<JwzxTeacher> getTeacherInfo(String keyword) {
        // 发起链接
        String json = jwzxService.get(TEACHER_INFO_URL + keyword).text();
        String teacherArrayJson = JSON.parseObject(json).get("returnData").toString();
        List<JwzxTeacher> jwzxTeachers = JSON.parseArray(teacherArrayJson, JwzxTeacher.class);
        return jwzxTeachers;
    }
}
