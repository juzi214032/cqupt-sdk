package com.juzi.cqupt.sdk.jwzx.bean.news;

import lombok.Data;

import java.util.List;

/**
 * 教务在线新闻列表结果封装
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/11/27 0:49
 */
@Data
public class JwzxNewsListResult {

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 新闻列表数据
     */
    private List<JwzxNewsList> data;
}
