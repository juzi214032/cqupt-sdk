package com.juzi.library.api;

import com.juzi.library.bean.LibPersonInfo;

import java.io.IOException;

/**
 * 个人信息接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/17 22:19
 */
public interface LibPersonInfoService {

    /**
     * 个人信息页url
     */
    String PERSONAL_INFO_PAGE_URL = "/reader/redr_info.php";

    /**
     * 获取个人信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 个人信息
     * @throws IOException
     */
    LibPersonInfo getPersonInfo(String username, String password);

}
