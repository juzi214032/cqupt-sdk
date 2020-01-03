package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaGovernmentService;
import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.bean.OaGovernment;
import com.juzi.cqupt.sdk.oa.bean.OaGovernmentFile;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Juzi
 * @since 2019/12/30 19:06
 * Blog https://juzibiji.top
 */
@Slf4j
public class OaGovernmentServiceImpl implements OaGovernmentService {

    private OaService oaService;

    public OaGovernmentServiceImpl(OaService oaService) {
        this.oaService = oaService;
    }

    @Override
    public List<OaGovernment> getList(int pageOn) {
        log.debug("开始获取[党政发文]列表");
        List<OaGovernment> oaGovernmentList = new ArrayList<>();
        // 访问网页
        Document document = this.oaService.get(GOVERNMENT_LIST_URL + pageOn);
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

            OaGovernment government = new OaGovernment();
            government
                    .setReleaseTime(releaseTime)
                    .setTitle(title)
                    .setReleaseDepartment(releaseDepartment)
                    .setReleasePerson(releasePerson)
                    .setId(id);
            oaGovernmentList.add(government);
        }
        log.debug("获取[事务通知]列表结束");
        return oaGovernmentList;
    }

    @Override
    public OaGovernment getDetail(String id) {
        log.debug("开始获取[党政发文]详情");
        OaGovernment government = new OaGovernment();
        Document document = this.oaService.get(GOVERNMENT_DETAIL_URL + id);
        // 标题
        String title = document.select("h2").text();

        //文件中的附件
        List<OaGovernmentFile> fileList = new ArrayList<>();
        Elements fileElements = document.select("a[href~=downLoadById]");
        for (Element fileElement : fileElements) {
            OaGovernmentFile oaGovernmentFile = new OaGovernmentFile();
            String fileName = fileElement.text();
            String fileNo = fileElement.attr("href").substring(67);
            oaGovernmentFile.setFileName(fileName).setGovernmentId(id).setFileNo(fileNo);
            fileList.add(oaGovernmentFile);
        }

        // 详细信息
        Elements detailInfo = document.select(".content_area>table:nth-of-type(1)>tbody>tr>td tr td");
        government
                .setId(id)
                .setTitle(title)
                .setProcessName(detailInfo.get(1).text())
                .setContentNo(detailInfo.get(3).text())
                .setReleaseTime(detailInfo.get(5).text())
                .setPriorities(detailInfo.get(7).text())
                .setConfidentialityLevel(detailInfo.get(9).text())
                .setMainSending(detailInfo.get(13).text())
                .setCopySending(detailInfo.get(15).text())
                .setReleasePerson(detailInfo.get(17).text())
                .setVerifier(detailInfo.get(19).text())
                .setReleaseDepartment(detailInfo.get(21).text())
                .setCountersign(detailInfo.get(23).text())
                .setReviewer(detailInfo.get(25).text())
                .setIssuePerson(detailInfo.get(27).text())
                .setFileList(fileList);

        log.debug("结束获取[党政发文]详情");
        return government;
    }

    @Override
    public int getTotalPage() {
        log.debug("党政发文-开始获取页码总数");
        Document document = this.oaService.get(GOVERNMENT_LIST_URL + 1);
        String js = document.select(".content_area>.center>ul>script").toString();
        Matcher matcher = TOTAL_PAGE_PATTERN.matcher(js);
        if (matcher.find()) {
            double totalPageDouble = Integer.parseInt(matcher.group()) / 20D;
            double totalPageInt = Math.ceil(totalPageDouble);
            log.debug("党政发文-获取页码总数成功，页码总数为[{}]", (int) totalPageInt);
            return (int) totalPageInt;
        }
        log.warn("党政发文-获取页码总数失败，返回默认页码数0");
        return 0;
    }
}
