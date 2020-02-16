package com.github.juzi214032.cqupt.sdk.library.api.impl;

import com.github.juzi214032.cqupt.sdk.common.util.RegexUtil;
import com.github.juzi214032.cqupt.sdk.library.api.LibBookSearchService;
import com.github.juzi214032.cqupt.sdk.library.api.LibService;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfo;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfoResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/16 14:24
 */
public class LibBookSearchServiceImpl implements LibBookSearchService {

    private LibService libService;

    public LibBookSearchServiceImpl(LibService libService) {
        this.libService = libService;
    }

    @Override
    public LibBookInfoResult getBookBySearchKey(String keyword, Integer pageOn) {
        // 构造查询参数
        Map<String, String> queryData = new HashMap<>(INIT_QUERY_DATA);
        queryData.put("strText", keyword);
        queryData.put("page", pageOn.toString());

        // 构造结果集
        LibBookInfoResult bookInfoResult = new LibBookInfoResult();
        List<LibBookInfo> bookInfoList = new ArrayList<>();

        Document document = libService.get(SEARCH_URL, queryData);
        Elements bookElements = document.select(".book_list_info");
        for (Element bookElement : bookElements) {
            // 未处理的标题
            String rawTitle = bookElement.select("h3 a").text();
            String title = RegexUtil.parse(BOOK_TITLE_REGEX,rawTitle).get(0);
            // 索书号
            String id = bookElement.select("h3").get(0).ownText();
            // marcNo
            String marcNo = bookElement.select("h3 a").attr("href").substring(17);

            // 书籍详细信息数组
            String[] bookDetailArray = bookElement.select("p").get(0).ownText().split(" ");
            // 作者
            String author = bookDetailArray[0];
            // 出版社
            String press = bookDetailArray[1];
            // 出版日期
            String pressDate = bookDetailArray[2];

            // 副本详细信息数组
            String[] countDetail = bookElement.select("p span").get(0).ownText().split(" ");
            // 馆藏副本
            String allCount = countDetail[0].substring(4);
            // 可借副本
            String borrowCount = countDetail[1].substring(4);

            // 构造结果对象
            LibBookInfo bookInfo = new LibBookInfo();
            bookInfo
                    .setId(id)
                    .setTitle(title)
                    .setPress(press)
                    .setMarcNo(marcNo)
                    .setAuthor(author)
                    .setAllCount(allCount)
                    .setPressDate(pressDate)
                    .setBorrowCount(borrowCount);
            bookInfoList.add(bookInfo);
        }
        // 总页码
        String pageTotal = document.select(".pagination>b font[color=black]").text();
        bookInfoResult.setPageTotal(Integer.valueOf(pageTotal));
        bookInfoResult.setData(bookInfoList);
        return bookInfoResult;
    }
}
