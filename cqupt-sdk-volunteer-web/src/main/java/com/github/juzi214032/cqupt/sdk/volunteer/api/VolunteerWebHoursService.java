package com.github.juzi214032.cqupt.sdk.volunteer.api;

import com.github.juzi214032.cqupt.sdk.volunteer.bean.VolunteerWebHours;
import com.github.juzi214032.cqupt.sdk.volunteer.bean.VolunteerWebPageInfo;

import java.io.IOException;
import java.util.List;

/**
 * 时长接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 23:16
 */
public interface VolunteerWebHoursService {

    String HOURS_INFO_URL = "/app/user/hour.php";

    /**
     * 获取时长记录
     *
     * @param username 用户名
     * @param password 密码
     * @param pageNum  页码
     * @return 时长记录
     */
    List<VolunteerWebHours> getHoursInfo(String username, String password, int pageNum);

    /**
     * 获取分页信息
     *
     * @param username 账号
     * @param password 密码
     * @return 分页信息
     * @throws IOException
     */
    VolunteerWebPageInfo getPageInfo(String username, String password);
}
