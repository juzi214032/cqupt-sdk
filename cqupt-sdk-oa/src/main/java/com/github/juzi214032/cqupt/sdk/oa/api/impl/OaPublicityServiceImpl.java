package com.github.juzi214032.cqupt.sdk.oa.api.impl;

import com.github.juzi214032.cqupt.sdk.oa.api.OaPublicityService;
import com.github.juzi214032.cqupt.sdk.oa.api.OaService;
import com.github.juzi214032.cqupt.sdk.oa.bean.OaPublicity;
import com.github.juzi214032.cqupt.sdk.oa.bean.OaTransactionFile;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/11 9:27
 */
@Slf4j
public class OaPublicityServiceImpl extends OaNewsServiceImpl implements OaPublicityService {

    private OaService oaService;

    public OaPublicityServiceImpl(OaService oaService) {
        super(oaService);
        this.oaService = oaService;
    }

    @Override
    public List<OaPublicity> getList(int onPage) {
        log.debug("开始获取[公示公告]列表");
        List<OaPublicity> oaPublicityList = new ArrayList<>();
        // 访问网页
        Document document = this.oaService.get(PUBLICITY_LIST_URL + onPage);
        Elements meetingElements = document.select(".content_area>.center li");
        for (Element meetingElement : meetingElements) {

            // 发布时间
            String releaseTime = meetingElement.select(".left").text();
            // 公示公告标题
            String title = meetingElement.select("a").text();
            // 会议id
            String id = meetingElement.select("a").attr("href").substring(37);

            log.debug("正在解析[公示公告]，当前解析标题[{}]", title);
            OaPublicity oaPublicity = new OaPublicity();
            oaPublicity
                    .setReleaseTime(releaseTime)
                    .setTitle(title)
                    .setId(id);
            oaPublicityList.add(oaPublicity);
        }
        log.debug("获取[公示公告]列表结束");
        return oaPublicityList;
    }

    @Override
    public OaPublicity getDetail(String id) {
        log.debug("开始获取[公示公告]详情");
        OaPublicity oaPublicity = new OaPublicity();
        // 事务通知详情必须要带上cookie才能访问，所以此处先访问事务通知列表页面拿到cookie
        Connection.Response response = this.oaService.excute(Connection.Method.GET, PUBLICITY_LIST_URL);
        Document document = this.oaService.get(PUBLICITY_DETAIL_URL + id, null, null, response.cookies());
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
            String fileNo = fileElement.attr("href").substring(63);
            oaTransactionFile.setFileName(fileName).setNotifyId(id).setFileNo(fileNo);
            fileList.add(oaTransactionFile);
        }

        // 详细信息
        Elements detailInfo = document.select(".content_area>table:nth-of-type(1)>tbody>tr>td tr");
        oaPublicity
                .setId(id)
                .setTitle(title)
                .setContent(content)
                .setReleasePerson(detailInfo.get(0).select("td").get(1).text())
                .setReleaseTime(detailInfo.get(0).select("td").get(3).text())
                .setReleaseDepartment(detailInfo.get(1).select("td").get(1).text())
                .setReviewer(detailInfo.get(1).select("td").get(3).text());

        log.debug("获取[公示公告]详情结束");
        return oaPublicity;
    }

    @Override
    public int getTotalPage() {
        return super.getTotalPage(PUBLICITY_LIST_URL);
    }
}
