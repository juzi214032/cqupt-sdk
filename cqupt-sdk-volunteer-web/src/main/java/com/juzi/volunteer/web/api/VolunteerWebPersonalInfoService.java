package com.juzi.volunteer.web.api;

import com.juzi.volunteer.web.bean.VolunteerWebPersonalInfo;

import java.io.IOException;

/**
 * 个人简要信息接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 13:20
 */
public interface VolunteerWebPersonalInfoService {

    /**
     * 个人信息页面url
     */
    String PERSONAL_INFO_URL = "/app/user/home.php";

    /**
     * 获取个人信息
     *
     * @param username 账号
     * @param password 密码
     * @return 个人信息
     * @throws IOException
     */
    VolunteerWebPersonalInfo getPersonalInfo(String username, String password);
}
