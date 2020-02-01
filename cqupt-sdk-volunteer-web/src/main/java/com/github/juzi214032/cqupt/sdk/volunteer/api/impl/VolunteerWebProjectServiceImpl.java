package com.github.juzi214032.cqupt.sdk.volunteer.api.impl;

import com.github.juzi214032.cqupt.sdk.volunteer.api.VolunteerWebProjectService;
import com.github.juzi214032.cqupt.sdk.volunteer.api.VolunteerWebService;
import com.github.juzi214032.cqupt.sdk.volunteer.bean.VolunteerWebPageInfo;
import com.github.juzi214032.cqupt.sdk.volunteer.bean.VolunteerWebProject;
import com.github.juzi214032.cqupt.sdk.volunteer.config.VolunteerWebConfig;
import com.github.juzi214032.cqupt.sdk.volunteer.constant.VolunteerWebConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 18:29
 */
public class VolunteerWebProjectServiceImpl implements VolunteerWebProjectService {

    private static final Pattern PAGE_INFO_PATTERN = Pattern.compile("(\\d+) 页 / (\\d+) 记录");
    private VolunteerWebService volunteerWebService;
    private VolunteerWebConfig volunteerWebConfig;

    public VolunteerWebProjectServiceImpl(VolunteerWebService volunteerWebService) {
        this.volunteerWebService = volunteerWebService;
        volunteerWebConfig = volunteerWebService.getConfig();
    }

    @Override
    public List<VolunteerWebProject> getMyProject(String username, String password, int pageNum) throws IOException {
        List<VolunteerWebProject> volunteerWebProjectList = new ArrayList<>();
        String cookie = volunteerWebService.login(username, password);
        Document document = Jsoup
                .connect(volunteerWebConfig.getDomain() + PROJECT_INFO_URL)
                .cookie(VolunteerWebConstant.COOKIE_NAME, cookie)
                .data("p", String.valueOf(pageNum))
                .get();

        // 从索引1开始遍历
        Elements projectElements = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");

        for (int i = 1; i < projectElements.size(); i++) {

            VolunteerWebProject volunteerWebProject = new VolunteerWebProject();

            Elements projectTds = projectElements.get(i).getElementsByTag("td");

            // 项目id
            String projectId = projectTds.get(0).getElementsByTag("a").attr("href").substring(21);
            // 项目标题
            String title = projectTds.get(0).getElementsByTag("a").get(0).ownText();
            // 项目发起组织
            String organization = projectTds.get(0).ownText().substring(5);

            String[] contactInfoArray = projectTds.get(1).ownText().split(" ");
            // 联系人姓名
            String contactName = contactInfoArray[0];
            // 联系人电话
            String contactPhone = null;
            if (contactInfoArray.length > 1) {
                contactPhone = contactInfoArray[1].substring(3);
            }
            // 加入项目时间
            String joinTime = projectTds.get(2).ownText();
            // 项目状态
            String projectStatus = projectTds.get(3).text();
            // 岗位
            String job = projectTds.get(4).ownText();
            // 时长
            String hours = projectTds.get(5).text();

            volunteerWebProject
                    .setProjectId(projectId)
                    .setTitle(title)
                    .setContactName(contactName)
                    .setContactPhone(contactPhone)
                    .setHours(hours)
                    .setProjectStatus(projectStatus)
                    .setOrganization(organization)
                    .setJoinDate(joinTime)
                    .setJob(job);

            volunteerWebProjectList.add(volunteerWebProject);
        }
        return volunteerWebProjectList;
    }

    @Override
    public VolunteerWebPageInfo getPageInfo(String username, String password) throws IOException {
        VolunteerWebPageInfo volunteerWebPageInfo = new VolunteerWebPageInfo();
        String cookie = volunteerWebService.login(username, password);
        Document document = Jsoup
                .connect(volunteerWebConfig.getDomain() + PROJECT_INFO_URL)
                .cookie(VolunteerWebConstant.COOKIE_NAME, cookie)
                .get();

        String pageInfo = document.getElementsByAttributeValueContaining("class", "ptpage").text();
        Matcher matcher = PAGE_INFO_PATTERN.matcher(pageInfo);
        matcher.find();
        String pageNumTotal = matcher.group(1);
        String projectNumTotal = matcher.group(2);

        volunteerWebPageInfo
                .setPageNumTotal(Integer.parseInt(pageNumTotal))
                .setRecordNumTotal(Integer.parseInt(projectNumTotal));
        return volunteerWebPageInfo;
    }
}
