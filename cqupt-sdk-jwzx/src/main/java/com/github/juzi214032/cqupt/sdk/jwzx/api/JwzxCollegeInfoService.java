package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.college.JwzxCollegeInfo;

import java.util.List;

/**
 * 学院信息接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/3 15:28
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
