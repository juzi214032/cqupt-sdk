package com.github.juzi214032.cqupt.sdk.library.api;

import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfoResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 图书搜索
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/16 14:08
 */
public interface LibBookSearchService {

    /**
     * 搜索页url
     */
    String SEARCH_URL = "/opac/openlink.php";

    /**
     * 提取书籍名称的正则
     */
    Pattern BOOK_TITLE_REGEX = Pattern.compile("(?:\\d*).(.*)");

    /**
     * 搜索请求默认参数
     */
    Map<String, String> INIT_QUERY_DATA = new HashMap<String, String>() {{
        put("displaypg", "20");
        put("doctype", "ALL");
        put("lang_code", "ALL");
        put("location", "ALL");
        put("match_flag", "forward");
        put("onlylendable", "no");
        put("orderby", "DESC");
        put("showmode", "list");
        put("sort", "CATA_DATE");
        put("strSearchType", "title");
    }};

    /**
     * 搜索图书
     *
     * @param keyword 搜索关键词
     * @param pageOn  当前页码
     * @return 图书
     */
    LibBookInfoResult getBookBySearchKey(String keyword, Integer pageOn);
}
