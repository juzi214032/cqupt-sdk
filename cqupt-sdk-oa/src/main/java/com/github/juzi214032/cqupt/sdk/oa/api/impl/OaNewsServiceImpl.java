package com.github.juzi214032.cqupt.sdk.oa.api.impl;

import com.github.juzi214032.cqupt.sdk.oa.api.OaNewsService;
import com.github.juzi214032.cqupt.sdk.oa.api.OaService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/11 10:13
 */
@Slf4j
public class OaNewsServiceImpl implements OaNewsService {
    private OaService oaService;

    public OaNewsServiceImpl(OaService oaService) {
        this.oaService = oaService;
    }

    @Override
    public int getTotalPage(String url) {
        log.debug("开始获取总页数");
        Document document = this.oaService.get(url + 1);
        String js = document.select(".content_area>.center>ul>script").toString();
        Matcher matcher = TOTAL_PAGE_PATTERN.matcher(js);
        if (matcher.find()) {
            double totalPageDouble = Integer.parseInt(matcher.group()) / 20D;
            double totalPageInt = Math.ceil(totalPageDouble);
            log.debug("结束获取总页数");
            return (int) totalPageInt;
        }
        log.warn("获取总页数失败");
        return 0;
    }
}
