package com.github.juzi214032.cqupt.sdk.library.api.impl;

import com.github.juzi214032.cqupt.sdk.library.api.LibRecommendBuyService;
import com.github.juzi214032.cqupt.sdk.library.api.LibService;
import com.github.juzi214032.cqupt.sdk.library.bean.recommend.LibRecommendBuy;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/23 0:20
 */
@Slf4j
public class LibRecommendBuyServiceImpl implements LibRecommendBuyService {

    private LibService libService;

    public LibRecommendBuyServiceImpl(LibService libService) {
        this.libService = libService;
    }

    @Override
    public List<LibRecommendBuy> getRecommendBuyList(int pageOn) {
        List<LibRecommendBuy> recommendBuyList = new ArrayList<>(20);
        Document document = this.libService.get(RECOMMEND_BUY_LIST_URL+pageOn);
        Elements trElements = document.select("tbody tr:not(:first-of-type)");
        for (Element trElement : trElements) {
            LibRecommendBuy libRecommendBuy = new LibRecommendBuy();
            Elements tdElements = trElement.select("td");
            libRecommendBuy
                    .setTitle(tdElements.get(1).text())
                    .setAuthor(tdElements.get(2).text())
                    .setPress(tdElements.get(3).text())
                    .setDate(tdElements.get(4).text())
                    .setStatus(tdElements.get(5).text())
                    .setRemark(tdElements.get(6).text());
            recommendBuyList.add(libRecommendBuy);
        }
        return recommendBuyList;
    }

    @Override
    public String getTotalPage() {
        Document document = this.libService.get(RECOMMEND_BUY_LIST_URL+1);
        return document.select(".numstyle b font[color=black]").text();
    }
}
