package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaDispatchService;
import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.bean.OaDispatch;
import com.juzi.cqupt.sdk.oa.bean.OaDispatchFile;
import com.juzi.cqupt.sdk.oa.enums.NewsType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/30 19:06
 */
@Slf4j
public class OaDispatchServiceImpl extends OaNewsServiceImpl implements OaDispatchService {

    private OaService oaService;

    public OaDispatchServiceImpl(OaService oaService) {
        super(oaService);
        this.oaService = oaService;
    }

    @Override
    public List<OaDispatch> getList(NewsType type, int pageOn) {
        log.debug("开始获取[发文]列表");
        List<OaDispatch> oaDispatchList = new ArrayList<>();
        // 访问网页
        Document document = null;
        if (type == NewsType.GOVERNMENT) {
            document = this.oaService.get(GOVERNMENT_LIST_URL + pageOn);
        } else if (type == NewsType.ALLIANCE) {
            document = this.oaService.get(ALLIANCE_LIST_URL + pageOn);
        }
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

            log.debug("正在解析[发文]，当前解析标题[{}]", title);

            OaDispatch government = new OaDispatch();
            government
                    .setReleaseTime(releaseTime)
                    .setTitle(title)
                    .setReleaseDepartment(releaseDepartment)
                    .setReleasePerson(releasePerson)
                    .setId(id);
            oaDispatchList.add(government);
        }
        log.debug("获取[发文]列表结束");
        return oaDispatchList;
    }

    @Override
    public OaDispatch getDetail(NewsType type, String id) {
        log.debug("开始获取[发文]详情");
        OaDispatch government = new OaDispatch();
        Document document = null;
        if (type == NewsType.GOVERNMENT) {
            document = this.oaService.get(GOVERNMENT_DETAIL_URL + id);
        } else if (type == NewsType.ALLIANCE) {
            document = this.oaService.get(ALLIANCE_DETAIL_URL + id);
        } else {
            return null;
        }
        // 标题
        String title = document.select("h2").text();

        //文件中的附件
        List<OaDispatchFile> fileList = new ArrayList<>();
        Elements fileElements = document.select("a[href~=downLoadById]");
        for (Element fileElement : fileElements) {
            OaDispatchFile oaDispatchFile = new OaDispatchFile();
            String fileName = fileElement.text();
            String fileNo = fileElement.attr("href").substring(67);
            oaDispatchFile.setFileName(fileName).setGovernmentId(id).setFileNo(fileNo);
            fileList.add(oaDispatchFile);
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

        log.debug("结束获取[发文]详情");
        return government;
    }

    @Override
    public int getTotalPage(NewsType type) {
        if (type == NewsType.GOVERNMENT) {
            return super.getTotalPage(GOVERNMENT_LIST_URL);
        } else if (type == NewsType.ALLIANCE) {
            return super.getTotalPage(ALLIANCE_LIST_URL);
        } else {
            return 0;
        }
    }
}
