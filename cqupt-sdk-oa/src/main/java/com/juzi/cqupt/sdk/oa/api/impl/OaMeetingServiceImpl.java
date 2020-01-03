package com.juzi.cqupt.sdk.oa.api.impl;

import com.juzi.cqupt.sdk.oa.api.OaMeetingService;
import com.juzi.cqupt.sdk.oa.api.OaService;
import com.juzi.cqupt.sdk.oa.bean.OaMeeting;
import com.juzi.cqupt.sdk.oa.bean.OaMeetingFile;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi
 * @since 2019/12/28 22:48
 * Blog https://juzibiji.top
 */
@Slf4j
public class OaMeetingServiceImpl implements OaMeetingService {
    private OaService oaService;

    public OaMeetingServiceImpl(OaService oaService) {
        this.oaService = oaService;
    }

    @Override
    public List<OaMeeting> getList(int pageOn) {
        log.debug("开始获取[会议通知]列表");
        List<OaMeeting> meetingList = new ArrayList<>();
        // 访问网页
        Document document = this.oaService.get(MEETING_LIST_URL + pageOn);
        Elements meetingElements = document.select(".content_area>.center li");
        for (Element meetingElement : meetingElements) {
            // 发布时间
            String releaseTime = meetingElement.select(".left").text();
            // 会议标题
            String title = meetingElement.select("a").text();
            // 会议id
            String id = meetingElement.select("a").attr("href").substring(40);

            log.debug("正在解析[会议通知]，当前解析标题[{}]", title);

            OaMeeting meeting = new OaMeeting();
            meeting.setReleaseTime(releaseTime).setTitle(title).setId(id);
            meetingList.add(meeting);
        }
        log.debug("获取[会议通知]列表结束");
        return meetingList;
    }

    @Override
    public OaMeeting getDetail(String meetingId) {
        log.debug("开始获取[会议通知]详情");
        OaMeeting meeting = new OaMeeting();
        Document document = this.oaService.get(MEETING_DETAIL_URL + meetingId);
        String title = document.select("h2").text();
        String content = document.select(".content_area>table table").get(1).select("td").html();
        Elements detailInfo = document.select(".content_area>table table").get(0).select("tr td");

        //文件中的附件
        List<OaMeetingFile> fileList = new ArrayList<>();
        Elements fileElements = document.select(".slave a");
        for (Element fileElement : fileElements) {
            OaMeetingFile oaMeetingFile = new OaMeetingFile();
            String fileName = fileElement.text();
            String fileNo = fileElement.attr("href").substring(65);
            oaMeetingFile.setFileName(fileName).setMeetingId(meetingId).setFileNo(fileNo);
            fileList.add(oaMeetingFile);
        }

        meeting
                .setId(meetingId)
                .setTitle(title)
                .setContent(content)
                .setReleaseTime(detailInfo.get(1).text())
                .setReleasePerson(detailInfo.get(3).text())
                .setMeetingTime(detailInfo.get(5).text())
                .setReleaseDepartment(detailInfo.get(7).text())
                .setMeetingPlace(detailInfo.get(9).text())
                .setReviewer(detailInfo.get(11).text())
                .setPresenter(detailInfo.get(13).text())
                .setFileList(fileList);

        log.debug("获取[会议通知]详情结束");
        return meeting;
    }

}
