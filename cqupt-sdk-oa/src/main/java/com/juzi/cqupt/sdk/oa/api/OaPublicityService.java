package com.juzi.cqupt.sdk.oa.api;

import com.juzi.cqupt.sdk.oa.bean.OaPublicity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 公示公告
 *
 * @author Juzi
 * @date 2019/12/30 19:34
 * Blog https://juzibiji.top
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
     * 提取页码总数正则
     */
    Pattern TOTAL_PAGE_PATTERN = Pattern.compile("(?<=ShowoPage\\().*?(?=,)");

    /**
     * 获取列表
     *
     * @param onPage 页码
     * @return
     */
    List<OaPublicity> getList(int onPage);

    /**
     * @return 公示公告详情
     */
    OaPublicity getDetail();

    /**
     * @return 总页码
     */
    int getTotalPage();
}
