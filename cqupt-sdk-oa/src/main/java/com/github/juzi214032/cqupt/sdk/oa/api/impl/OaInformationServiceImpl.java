package com.github.juzi214032.cqupt.sdk.oa.api.impl;

import cn.hutool.core.util.ReUtil;
import com.github.juzi214032.cqupt.sdk.oa.api.OaInformationService;
import com.github.juzi214032.cqupt.sdk.oa.bean.OaInformation;
import com.github.juzi214032.cqupt.sdk.oa.enums.NewsType;
import com.github.juzi214032.cqupt.sdk.oa.api.OaService;
import com.github.juzi214032.cqupt.sdk.oa.bean.OaInformationFile;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/11 12:40
 */
@Slf4j
public class OaInformationServiceImpl extends OaNewsServiceImpl implements OaInformationService {

    private OaService oaService;

    public OaInformationServiceImpl(OaService oaService) {
        super(oaService);
        this.oaService = oaService;
    }

    @Override
    public List<OaInformation> getList(NewsType newsType, int pageOn) {
        log.debug("开始获取[{}]列表，第[{}]页", newsType.getName(), pageOn);
        List<OaInformation> oaInformationList = new ArrayList<>();
        // 访问网页
        Document document = this.oaService.get(newsType.getListUrl() + pageOn);

        Elements meetingElements = document.select(".content_area>.center li");
        for (Element meetingElement : meetingElements) {
            // 发布时间
            String releaseTime = meetingElement.select(".left").text();
            // 公示公告标题
            String title = meetingElement.select("a").text();
            // 会议id
            String id = ReUtil.extractMulti(INFORMATION_ID_PATTERN, meetingElement.select("a").attr("href"), "$1");

            log.debug("正在解析[{}]，当前解析标题[{}]", newsType.getName(), title);
            OaInformation oaInformation = new OaInformation();
            oaInformation
                    .setReleaseTime(releaseTime)
                    .setTitle(title)
                    .setId(id);
            oaInformationList.add(oaInformation);
        }
        log.debug("结束获取[{}]列表，第[{}]页", newsType.getName(), pageOn);
        return oaInformationList;
    }

    @Override
    public OaInformation getDetail(NewsType newsType, String id) {
        log.debug("开始获取[{}]详情，文章id[{}]", newsType.getName(), id);
        OaInformation oaInformation = new OaInformation();
        // 事务通知详情必须要带上cookie才能访问，所以此处先访问事务通知列表页面拿到cookie
        Connection.Response response = this.oaService.excute(Connection.Method.GET, newsType.getListUrl());
        Document document = this.oaService.get(newsType.getDetailUrl() + id, null, null, response.cookies());
        // 标题
        String title = document.select("h2").text();
        // 内容
        String content = document.select(".content_area>table:nth-of-type(2) td").html();

        //文件中的附件
        List<OaInformationFile> fileList = new ArrayList<>();
        Elements fileElements = document.select("a[href~=down]");
        for (Element fileElement : fileElements) {
            OaInformationFile oaInformationFile = new OaInformationFile();
            String fileName = fileElement.text();
            String fileNo = ReUtil.extractMulti(FILE_ID_PATTERN, fileElement.attr("href"), "$1");
            oaInformationFile.setFileName(fileName).setInformationId(id).setFileId(fileNo);
            fileList.add(oaInformationFile);
        }

        // 详细信息
        Elements detailInfo = document.select(".content_area>table:nth-of-type(1)>tbody>tr>td tr");
        oaInformation
                .setId(id)
                .setTitle(title)
                .setFiles(fileList)
                .setContent(content)
                .setReleasePerson(detailInfo.get(0).select("td").get(1).text())
                .setReleaseTime(detailInfo.get(0).select("td").get(3).text())
                .setReleaseDepartment(detailInfo.get(1).select("td").get(1).text())
                .setReviewer(detailInfo.get(1).select("td").get(3).text());

        log.debug("结束获取[{}]详情，文章id[{}]", newsType.getName(), id);
        return oaInformation;
    }

    @Override
    public int getTotalPage(NewsType newsType) {
        log.debug("开始获取[{}]分页信息", newsType.getName());
        int totalPage = super.getTotalPage(newsType.getListUrl());
        log.debug("结束获取[{}]分页信息", newsType.getName());
        return totalPage;
    }
}
