package com.github.juzi214032.cqupt.sdk.oa.api;

import java.util.regex.Pattern;

/**
 * 所有OA资讯基类
 *
 * @author Juzi - https://juzibiji.top
 * @date 2020/1/11 10:13
 */
public interface OaNewsService {
    /**
     * 提取页码总数正则
     */
    Pattern TOTAL_PAGE_PATTERN = Pattern.compile("(?<=ShowoPage\\().*?(?=,)");

    /**
     * 获取会议通知、事务通知等的总页数
     *
     * @param url 列表页url
     * @return 总页数
     */
    int getTotalPage(String url);

}
