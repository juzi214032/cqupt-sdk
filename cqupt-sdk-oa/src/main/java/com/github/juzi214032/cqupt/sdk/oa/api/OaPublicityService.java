package com.github.juzi214032.cqupt.sdk.oa.api;

import com.github.juzi214032.cqupt.sdk.oa.bean.OaPublicity;

import java.util.List;

/**
 * 公示公告
 *
 * @author Juzi - https://juzibiji.top
 * @date 2019/12/30 19:34
 */
public interface OaPublicityService {
    /**
     * 列表uri
     */
    String PUBLICITY_LIST_URL = "/notify.do?method=queryAllNotify&type=2&curPageNo=";
    /**
     * 详情uril
     */
    String PUBLICITY_DETAIL_URL = "/notify.do?method=oneNotify&notifyId=";

    /**
     * 获取列表
     *
     * @param onPage 页码
     * @return
     */
    List<OaPublicity> getList(int onPage);

    /**
     * @param id 公示公告id
     * @return 公示公告详情
     */
    OaPublicity getDetail(String id);

    /**
     * 获取公示公告总页数
     *
     * @return 总页码
     */
    int getTotalPage();
}
