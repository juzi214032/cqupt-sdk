package com.github.juzi214032.cqupt.sdk.oa.api;

import com.github.juzi214032.cqupt.sdk.oa.bean.OaDispatch;
import com.github.juzi214032.cqupt.sdk.oa.enums.NewsType;

import java.util.List;

/**
 * 党政发文&群团发文
 *
 * @author Juzi - https://juzibiji.top
 * @date 2019/12/30 19:04
 */
public interface OaDispatchService {

    /**
     * 党政发文列表uri
     */
    String GOVERNMENT_LIST_URL = "/dispatch.do?method=queryPublicDispatch&type=(1,2,3)&curPageNo=";
    /**
     * 党政发文详情uri
     */
    String GOVERNMENT_DETAIL_URL = "/dispatch.do?method=oneDispatch&mk=(1,2,3)&dispatchId=";

    /**
     * 党政发文列表uri
     */
    String ALLIANCE_LIST_URL = "/dispatch.do?method=queryPublicDispatch&type=(4,5)&curPageNo=";
    /**
     * 党政发文详情uri
     */
    String ALLIANCE_DETAIL_URL = "/dispatch.do?method=oneDispatch&mk=(1,2,3)&dispatchId=";

    /**
     * 获取发文列表
     *
     * @param type   发文类型
     * @param pageOn 页码
     * @return
     */
    List<OaDispatch> getList(NewsType type, int pageOn);

    /**
     * 获取发文详情
     *
     * @param type 发文类型
     * @param id   发文id
     * @return
     */
    OaDispatch getDetail(NewsType type, String id);

    /**
     * 获取发文总页数
     *
     * @param type 发文类型
     * @return 总页码
     */
    int getTotalPage(NewsType type);
}
