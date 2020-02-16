package com.github.juzi214032.cqupt.sdk.library.api.impl;

import com.github.juzi214032.cqupt.sdk.common.util.RegexUtil;
import com.github.juzi214032.cqupt.sdk.library.api.LibBookSearchService;
import com.github.juzi214032.cqupt.sdk.library.api.LibService;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfo;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookInfoResult;
import com.github.juzi214032.cqupt.sdk.library.bean.book.LibBookLocation;
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
    public LibBookInfoResult getBookListBySearchKey(String keyword, Integer pageOn) {
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
            String title = RegexUtil.parse(BOOK_TITLE_REGEX, rawTitle).get(0);
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

    @Override
    public List<LibBookLocation> getBookLocationByMarcNo(String marcNo) {

        // 构造结果
        List<LibBookLocation> bookLocationList = new ArrayList<>();

        Document document = libService.get(BOOK_LOCATION_URL + marcNo);
        Elements trElements = document.select("tr");
        for (int i = 1; i < trElements.size(); i++) {
            Element trElement = trElements.get(i);
            Elements tdElements = trElement.select("td");

            // 索书号
            String id = tdElements.get(0).text();
            // 条码号
            String barCode = tdElements.get(1).text();
            // 馆藏地
            String place = tdElements.get(3).text();
            // 书籍状态
            String status = tdElements.get(4).text();
            // 应还日期
            String returnDate = "";
            if (status.contains("应还日期")) {
                List<String> result = RegexUtil.parse(BOOK_STATUS_REGEX, status);
                status = result.get(0);
                returnDate=result.get(1);
            }

            // 构造结果
            LibBookLocation bookLocation = new LibBookLocation();
            bookLocation
                    .setId(id)
                    .setPlace(place)
                    .setStatus(status)
                    .setBarCode(barCode)
                    .setReturnDate(returnDate);
            bookLocationList.add(bookLocation);
        }
        return bookLocationList;
    }

    @Override
    public LibBookInfo getBookInfoByMarcNo(String marcNo) {
        Document document = libService.get(BOOK_INFO_URL + marcNo);
        // 构造结果
        LibBookInfo bookInfo = new LibBookInfo();

        Elements dlElements = document.select("#item_detail dl");
        for (Element dlElement : dlElements) {
            String key = dlElement.select("dt").text();
            String value = dlElement.select("dd").text();
            if("题名/责任者:".equals(key)){
                String[] result = value.split("/");
                bookInfo.setTitle(result[0]);
                bookInfo.setAuthor(result[1]);
            }else if ("出版发行项:".equals(key)){
                String[] result = value.split(",");
                bookInfo.setPress(result[0]);
                bookInfo.setPressDate(result[1]);
            }else if ("ISBN及定价:".equals(key)){
                String[] result = value.split("/");
                bookInfo.setIsbn(result[0].replaceAll("-",""));
                bookInfo.setPrice(result[1].replaceAll("CNY",""));
            }else if("提要文摘附注:".equals(key)){
                bookInfo.setBrief(value);
            }
        }
        bookInfo.setMarcNo(marcNo);
        return bookInfo;
    }
}
