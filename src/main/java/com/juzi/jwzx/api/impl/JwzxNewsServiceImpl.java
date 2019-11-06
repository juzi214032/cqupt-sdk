package com.juzi.jwzx.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzi.jwzx.api.JwzxNewsService;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.bean.JwzxNews;
import com.juzi.jwzx.config.JwzxConfig;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juzi
 * @date 2019/9/6 19:29
 * Blog https://juzibiji.top
 */
public class JwzxNewsServiceImpl implements JwzxNewsService {
    
    private JwzxConfig jwzxConfig;
    
    private JwzxService jwzxService;
    
    public JwzxNewsServiceImpl(JwzxService jwzxService) {
        this.jwzxConfig = jwzxService.getConfig();
        this.jwzxService = jwzxService;
    }
    
    @Override
    public List<JwzxNews> getNewsList(int pageNo, int pageSize, String searchKey) {
        if (searchKey == null) {
            searchKey = "";
        }
        
        Map<String, String> data = new HashMap<>(3);
        data.put("pageNo", String.valueOf(pageNo));
        data.put("pageSize", String.valueOf(pageSize));
        data.put("searchKey", searchKey);
        
        String resultJson = jwzxService.get(NEWS_LIST_API, data).text();
        
        JSONObject resultObject = JSONObject.parseObject(resultJson);
        return resultObject.getJSONArray("data").toJavaList(JwzxNews.class);
    }
    
    @Override
    public JwzxNews getNewsDetail(int id) {
        JwzxNews jwzxNews = new JwzxNews();
        
        Map<String, String> data = new HashMap<>(1);
        data.put("id", String.valueOf(id));
        Document document = jwzxService.get(NEWS_DETAIL_URL, data);
        
        // 标题
        String title = document.getElementsByTag("h3").get(0).text();
        // 内容
        String content = document.getElementById("mainPanel").wholeText();
        // 时间+作者+阅读量
        String[] newsInfo = document.getElementsByTag("p").get(0).text().split(" ");
        
        jwzxNews
                .setNewsId(id)
                .setTitle(title)
                .setContent(content)
                .setTime(newsInfo[0].replaceAll("发布时间：", "") + " " + newsInfo[1].substring(0, 8))
                .setAuthor(newsInfo[2].replaceAll("发布人：", ""))
                .setViews(Integer.valueOf(newsInfo[3].replaceAll("阅读数：", "")));
        
        return jwzxNews;
    }
}
