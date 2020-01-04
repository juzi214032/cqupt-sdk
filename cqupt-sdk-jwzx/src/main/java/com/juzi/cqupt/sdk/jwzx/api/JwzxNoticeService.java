package com.juzi.cqupt.sdk.jwzx.api;

import java.util.List;

/**
 * 教务在线首页的公告
 *
 * @author Juzi
 * @date 2020/1/4 21:23
 * Blog https://juzibiji.top
 */
public interface JwzxNoticeService {

    /**
     * 获取教务在线首页右侧的公告
     *
     * @return 公告列表
     */
    List<String> getNoticeList();

}
