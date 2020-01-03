package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNews;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNewsListResult;
import org.jsoup.Connection;

/**
 * 教务在线新闻
 *
 * @author Juzi
 * @since 2019/9/6 19:17
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
    JwzxNewsListResult getNewsList(int pageNo, int pageSize, String searchKey);

    /**
     * 获取某个文章详情
     *
     * @param id          文章id
     * @param fullContent 是否开启完整内容
     *                    该字段为true时，content变量会包含标题、发布人等信息
     *                    该字段为false时，content仅包含正文内容和文件列表
     * @return 文章详情
     */
    JwzxNews getNewsDetail(int id, boolean fullContent);

    /**
     * 下载文件
     *
     * @param fileId 文件id
     * @return
     */
    Connection.Response downloadFile(Integer fileId);

}
