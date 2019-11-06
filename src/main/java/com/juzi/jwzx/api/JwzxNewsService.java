package com.juzi.jwzx.api;

import com.juzi.jwzx.bean.JwzxNews;

import java.util.List;

/**
 * 教务在线新闻
 *
 * @author Juzi
 * @date 2019/9/6 19:17
 * Blog https://juzibiji.top
 */
public interface JwzxNewsService {
    
    /**
     * 新闻列表api
     */
    String NEWS_LIST_API = "/data/json_files.php";
    
    /**
     * 新闻详情url
     */
    String NEWS_DETAIL_URL = "/fileShowContent.php";
    
    /**
     * 获取新闻列表
     *
     * @param pageNo    页码
     * @param pageSize  每页条数
     * @param searchKey 搜索关键字（不搜索传空字符串即可）
     * @return 新闻列表
     */
    List<JwzxNews> getNewsList(int pageNo, int pageSize, String searchKey);
    
    /**
     * 获取某个文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    JwzxNews getNewsDetail(int id);
}
