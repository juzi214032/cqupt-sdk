package com.github.juzi214032.cqupt.sdk.library.api;

import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfo;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfoResult;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookLocation;

import java.util.HashMap;
import java.util.List;
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
     * 书籍馆藏位置信息url
     */
    String BOOK_LOCATION_URL = "/opac/ajax_item.php?marc_no=";

    /**
     * 书籍详情url
     */
    String BOOK_INFO_URL = "/opac/item.php?marc_no=";

    /**
     * 提取书籍名称的正则
     */
    Pattern BOOK_TITLE_REGEX = Pattern.compile("(?:\\d*).(.*)");

    /**
     * 书籍应还日期正则
     */
    Pattern BOOK_STATUS_REGEX = Pattern.compile("(.*)-应还日期：(.*)");

    /**
     * 搜索请求默认参数
     */
    Map<String, String> INIT_QUERY_DATA = new HashMap<String, String>() {
        {
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
    LibBookInfoResult getBookListBySearchKey(String keyword, Integer pageOn);

    /**
     * 获取书籍馆藏位置信息
     * @param marcNo 书籍编号
     * @return 馆藏位置信息
     */
    List<LibBookLocation> getBookLocationByMarcNo(String marcNo);

    /**
     * 获取书籍详情
     * @param marcNo 书籍编号
     * @return
     */
    LibBookInfo getBookInfoByMarcNo(String marcNo);
}
