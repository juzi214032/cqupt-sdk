package com.juzi.jwzx.api;

import com.juzi.jwzx.bean.college.JwzxCollegeInfo;

import java.util.List;

/**
 * 学院信息接口
 *
 * @author Juzi
 * @date 2019/8/3 15:28
 * Blog https://juzibiji.top
 */
public interface JwzxCollegeInfoService {
    
    String COLLEGE_INFO_URL = "/kebiao/index.php";
    
    /**
     * 获取学院信息
     *
     * @return 学院信息
     */
    List<JwzxCollegeInfo> getCollegeInfos();
    
}
