package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzi.cqupt.sdk.jwzx.api.JwzxNewsService;
import com.juzi.cqupt.sdk.jwzx.api.JwzxService;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNews;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNewsFile;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNewsList;
import com.juzi.cqupt.sdk.jwzx.bean.news.JwzxNewsListResult;
import com.juzi.cqupt.sdk.jwzx.config.JwzxConfig;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/6 19:29
 */
public class JwzxNewsServiceImpl implements JwzxNewsService {

    private static final String FILE_TYPE_DOC = "application/msword";
    private static final String FILE_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String FILE_TYPE_XLS = "application/vnd.ms-excel";
    private static final String FILE_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String FILE_TYPE_PDF = "application/pdf";
    private static final String FILE_TYPE_ZIP = "application/zip";
    private static final String FILE_TYPE_RAR = "application/octet-stream";
    private JwzxConfig jwzxConfig;
    private JwzxService jwzxService;

    public JwzxNewsServiceImpl(JwzxService jwzxService) {
        this.jwzxConfig = jwzxService.getConfig();
        this.jwzxService = jwzxService;
    }

    @Override
    public JwzxNewsListResult getNewsList(int pageNo, int pageSize, String searchKey) {
        if (searchKey == null) {
            searchKey = "";
        }

        Map<String, String> data = new HashMap<>(3);
        data.put("pageNo", String.valueOf(pageNo));
        data.put("pageSize", String.valueOf(pageSize));
        data.put("searchKey", searchKey);

        String resultJson = jwzxService.get(NEWS_LIST_API, data).text();

        JSONObject resultObject = JSONObject.parseObject(resultJson);
        Integer totalPage = resultObject.getInteger("totalPage");
        List<JwzxNewsList> newsList = resultObject.getJSONArray("data").toJavaList(JwzxNewsList.class);

        JwzxNewsListResult jwzxNewsListResult = new JwzxNewsListResult();
        jwzxNewsListResult.setTotalPage(totalPage);
        jwzxNewsListResult.setData(newsList);
        return jwzxNewsListResult;
    }

    @Override
    public JwzxNews getNewsDetail(int id, boolean fullContent) {
        JwzxNews jwzxNews = new JwzxNews();

        Map<String, String> data = new HashMap<>(1);
        data.put("id", String.valueOf(id));
        Document document = jwzxService.get(NEWS_DETAIL_URL, data);

        // 标题
        String title = document.getElementsByTag("h3").get(0).text();
        // 内容
        String content = "";
        // 时间+作者+阅读量
        String[] newsInfo = document.getElementsByTag("p").get(0).text().split(" ");

        // 正文html代码
        String contentHtml = document.getElementById("mainPanel").html().replaceAll("<!--\\[if gte mso [0-9]{1,2}\\]>[\\s\\S]*<!\\[endif\\]-->", "");
        // 将html代码解析为Jsoup的Document对象
        Document contentDocument = Jsoup.parseBodyFragment(contentHtml);
        // 删除上/下一页、文件列表
        contentDocument.select("ul").remove();
        // 删除最后一根分割线
        contentDocument.select("hr:last-of-type").remove();
        // 是否有附件文字
        boolean hasAttachment = contentDocument.select("p:last-of-type").html().contains("附件");
        if (hasAttachment) {
            contentDocument.select("p:last-of-type").remove();
        }

        if (!fullContent) {
            // 删除标题
            contentDocument.select("h3:first-of-type").remove();
            // 删除第一根分割线
            contentDocument.select("hr:first-of-type").remove();
            // 删除发布人等相关信息
            contentDocument.select("p:first-of-type").remove();
            //如果第一行为空行，则将其删除
            String firstLineText = contentDocument.select("p:first-of-type").get(0).text();
            if ("".equals(firstLineText)) {
                contentDocument.select("p:first-of-type").remove();
            }
        }
        content = contentDocument.select("body").get(0).html();

        Elements fileElements = document.select("#mainPanel").select("a[href~=fileDownLoad]");
        List<JwzxNewsFile> fileList = new ArrayList<>(fileElements.size());
        for (Element fileElement : fileElements) {
            JwzxNewsFile jwzxNewsFile = new JwzxNewsFile();

            String contentType = jwzxService.excute("/" + fileElement.attr("href"), Connection.Method.GET).contentType();
            switch (contentType) {
                case FILE_TYPE_DOC:
                    jwzxNewsFile.setType("doc");
                    break;
                case FILE_TYPE_DOCX:
                    jwzxNewsFile.setType("docx");
                    break;
                case FILE_TYPE_XLS:
                    jwzxNewsFile.setType("xls");
                    break;
                case FILE_TYPE_XLSX:
                    jwzxNewsFile.setType("xlsx");
                    break;
                case FILE_TYPE_PDF:
                    jwzxNewsFile.setType("pdf");
                    break;
                case FILE_TYPE_ZIP:
                    jwzxNewsFile.setType("zip");
                    break;
                case FILE_TYPE_RAR:
                    jwzxNewsFile.setType("rar");
                    break;
                default:
                    jwzxNewsFile.setType("unkonw");
                    break;
            }
            jwzxNewsFile
                    .setFileId(fileElement.attr("href").substring(26))
                    .setName(fileElement.text());
            fileList.add(jwzxNewsFile);
        }

        jwzxNews
                .setNewsId(id)
                .setTitle(title)
                .setContent(content)
                .setTime(newsInfo[0].replaceAll("发布时间：", "") + " " + newsInfo[1].substring(0, 8))
                .setAuthor(newsInfo[2].replaceAll("发布人：", ""))
                .setFileList(fileList)
                .setViews(Integer.valueOf(newsInfo[3].replaceAll("阅读数：", "")));

        return jwzxNews;
    }

    @Override
    public Connection.Response downloadFile(Integer fileId) {
        return this.jwzxService.excute("/fileDownLoadAttach.php?id=" + fileId, Connection.Method.GET);
    }
}
