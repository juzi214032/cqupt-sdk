package com.juzi.volunteer.web.api;

import com.juzi.volunteer.web.bean.VolunteerWebPageInfo;
import com.juzi.volunteer.web.bean.VolunteerWebProject;

import java.io.IOException;
import java.util.List;

/**
 * 项目接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 18:24
 */
public interface VolunteerWebProjectService {

    /**
     * 我的项目url
     */
    String PROJECT_INFO_URL = "/app/opp/opp.my.php";

    /**
     * 获取我的项目
     *
     * @param username 账号
     * @param password 密码
     * @param pageNum  页码
     * @return 我的项目
     * @throws IOException
     */
    List<VolunteerWebProject> getMyProject(String username, String password, int pageNum) throws IOException;

    /**
     * 获取分页信息
     *
     * @param username 账号
     * @param password 密码
     * @return 项目列表分页信息
     */
    VolunteerWebPageInfo getPageInfo(String username, String password) throws IOException;
}
