package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxNoticeService;
import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/4 21:24
 */
@Slf4j
public class JwzxNoticeServiceImpl implements JwzxNoticeService {

    private JwzxService jwzxService;

    public JwzxNoticeServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
    }

    @Override
    public List<String> getNoticeList() {
        log.debug("开始获取教务在线公告列表");

        Document document = this.jwzxService.get("");
        Elements noticeElements = document.select(".ads:nth-of-type(1) div:not(:first-of-type)");

        // 获取公告
        List<String> noticeList = new ArrayList<>();
        for (Element element : noticeElements) {
            noticeList.add(element.text());
        }

        log.debug("结束获取教务在线公告列表");
        return noticeList;
    }
}
