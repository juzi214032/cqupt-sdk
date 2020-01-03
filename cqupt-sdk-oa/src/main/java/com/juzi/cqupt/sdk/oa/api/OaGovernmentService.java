package com.juzi.cqupt.sdk.oa.api;

import com.juzi.cqupt.sdk.oa.bean.OaGovernment;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 党政发文
 *
 * @author Juzi
 * @date 2019/12/30 19:04
 * Blog https://juzibiji.top
 */
public interface OaGovernmentService {

    /**
     * 列表uri
     */
    String GOVERNMENT_LIST_URL = "/dispatch.do?method=queryPublicDispatch&type=(1,2,3)&curPageNo=";
    /**
     * 详情uri
     */
    String GOVERNMENT_DETAIL_URL = "/dispatch.do?method=oneDispatch&mk=(1,2,3)&dispatchId=";
    /**
     * 提取页码总数正则
     */
    Pattern TOTAL_PAGE_PATTERN = Pattern.compile("(?<=ShowoPage\\().*?(?=,)");

    /**
     * 获取党政发文列表
     *
     * @param pageOn 页码
     * @return
     */
    List<OaGovernment> getList(int pageOn);

    /**
     * 获取党政发文详情
     *
     * @param id 发文id
     * @return
     */
    OaGovernment getDetail(String id);

    /**
     * @return 页码总数
     */
    int getTotalPage();
}
