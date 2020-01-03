package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.api.OaTransactionService;
import com.juzi.cqupt.sdk.oa.bean.OaTransaction;
import com.juzi.cqupt.sdk.oa.bean.OaTransactionFile;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Juzi
 * @since 2019/12/29 1:00
 * Blog https://juzibiji.top
 */
@Slf4j
public class OaTransactionServiceImpl implements OaTransactionService {

    private OaService oaService;

    public OaTransactionServiceImpl(OaService oaService) {
        this.oaService = oaService;
    }

    @Override
    public List<OaTransaction> getList(int pageOn) {
        log.debug("开始获取[事务通知]列表");
        List<OaTransaction> oaTransactionList = new ArrayList<>();
        // 访问网页
        Document document = this.oaService.get(TRANSACTION_LIST_URL + pageOn);
        Elements meetingElements = document.select(".content_area>.center tr");
        // 第一行是表格标题，所以删除
        meetingElements.remove(0);
        for (Element meetingElement : meetingElements) {
            // 发布时间
            String releaseTime = meetingElement.select("td").get(0).text();
            // 会议标题
            String title = meetingElement.select("a").text();
            // 会议id
            String id = meetingElement.select("a").attr("href").substring(37);
            // 发布人
            String releasePerson = meetingElement.select("td").get(2).text();
            // 发布部门
            String releaseDepartment = meetingElement.select("td").get(3).text();

            log.debug("正在解析[事务通知]，当前解析标题[{}]", title);

            OaTransaction transaction = new OaTransaction();
            transaction
                    .setReleaseTime(releaseTime)
                    .setTitle(title)
                    .setReleaseDepartment(releaseDepartment)
                    .setReleasePerson(releasePerson)
                    .setId(id);
            oaTransactionList.add(transaction);
        }
        log.debug("获取[事务通知]列表结束");
        return oaTransactionList;
    }

    @Override
    public OaTransaction getDetail(String id) {
        log.debug("开始获取[事务通知]详情");
        OaTransaction transaction = new OaTransaction();
        // 事务通知详情必须要带上cookie才能访问，所以此处先访问事务通知列表页面拿到cookie
        Connection.Response response = this.oaService.excute(Connection.Method.GET, TRANSACTION_LIST_URL);
        Document document = this.oaService.get(TRANSACTION_DETAIL_URL + id, null, null, response.cookies());
        // 标题
        String title = document.select("h2").text();
        // 内容
        String content = document.select(".content_area>table").get(1).select("td").html();

        //文件中的附件
        List<OaTransactionFile> fileList = new ArrayList<>();
        Elements fileElements = document.select("a[href~=downLoadById]");
        for (Element fileElement : fileElements) {
            OaTransactionFile oaTransactionFile = new OaTransactionFile();
            String fileName = fileElement.text();
            String fileNo = fileElement.attr("href").substring(40);
            oaTransactionFile.setFileName(fileName).setNotifyId(id).setFileNo(fileNo);
            fileList.add(oaTransactionFile);
        }

        // 详细信息
        Elements detailInfo = document.select(".content_area>table:nth-of-type(1)>tbody>tr>td tr");
        transaction
                .setId(id)
                .setTitle(title)
                .setContent(content)
                .setProcessName(detailInfo.get(0).select("td").get(1).text())
                .setReleasePerson(detailInfo.get(2).select("td").get(1).text())
                .setReleaseTime(detailInfo.get(2).select("td").get(3).text())
                .setReleaseDepartment(detailInfo.get(3).select("td").get(1).text())
                .setReviewer(detailInfo.get(3).select("td").get(3).text());

        log.debug("获取[事务通知]详情结束");
        return transaction;
    }

    @Override
    public int getTotalPage() {
        Document document = this.oaService.get(TRANSACTION_LIST_URL + 1);
        String js = document.select(".content_area>.center>ul>script").toString();
        Matcher matcher = TOTAL_PAGE_PATTERN.matcher(js);
        if (matcher.find()) {
            double totalPageDouble = Integer.parseInt(matcher.group()) / 20D;
            double totalPageInt = Math.ceil(totalPageDouble);
            return (int) totalPageInt;
        }
        return 0;
    }
}
