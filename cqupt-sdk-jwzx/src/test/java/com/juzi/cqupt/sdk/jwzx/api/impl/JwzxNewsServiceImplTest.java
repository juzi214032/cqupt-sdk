package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxNewsService;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNews;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNewsListResult;
import org.jsoup.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwzxNewsServiceImplTest extends JwzxBaseTest {

    private JwzxNewsService jwzxNewsService = new JwzxNewsServiceImpl(jwzxService);

    @Test
    void getNewsList() throws IOException {
        JwzxNewsListResult newsList = jwzxNewsService.getNewsList(1, 20, "");
        assertNotEquals(0, newsList.getData().size(), "获取教务在线新闻列表失败");
        newsList.getData().forEach(System.out::println);
    }

    @Test
    void getNewsDetail() throws IOException {
        JwzxNews newsDetail = jwzxNewsService.getNewsDetail(6460, true);
        assertNotNull(newsDetail);
        System.out.println(newsDetail);
    }

    @Test
    void downloadFile() {
        Connection.Response response = jwzxNewsService.downloadFile(6444);
        System.out.println();
    }
}
