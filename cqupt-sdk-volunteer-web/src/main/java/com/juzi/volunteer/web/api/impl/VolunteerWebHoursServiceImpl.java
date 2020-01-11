package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebHoursService;
import com.juzi.volunteer.web.api.VolunteerWebService;
import com.juzi.volunteer.web.bean.VolunteerWebHours;
import com.juzi.volunteer.web.bean.VolunteerWebPageInfo;
import com.juzi.volunteer.web.config.VolunteerWebConfig;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 23:24
 */
public class VolunteerWebHoursServiceImpl implements VolunteerWebHoursService {

    private static final Pattern PAGE_INFO_PATTERN = Pattern.compile("(\\d+) 页 / (\\d+) 记录");
    private VolunteerWebConfig volunteerWebConfig;
    private VolunteerWebService volunteerWebService;

    public VolunteerWebHoursServiceImpl(VolunteerWebService volunteerWebService) {
        this.volunteerWebService = volunteerWebService;
        this.volunteerWebConfig = volunteerWebService.getConfig();
    }

    @Override
    public List<VolunteerWebHours> getHoursInfo(String username, String password, int pageNum) {
        List<VolunteerWebHours> volunteerWebHoursList = new ArrayList<>();

        Document document = volunteerWebService.get(HOURS_INFO_URL, username, password);
        Elements hoursElements = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (int i = 1; i < hoursElements.size(); i++) {
            VolunteerWebHours volunteerWebHours = new VolunteerWebHours();
            Elements hoursTds = hoursElements.get(i).getElementsByTag("td");
            // 时长
            String hours = hoursTds.get(0).ownText();
            hours = hours.substring(0, hours.length() - 2);
            // 备注
            String note = hoursTds.get(0).getElementsByTag("font").get(0).ownText();
            // 添加方式
            String addWay = hoursTds.get(1).text();
            // 状态
            String hoursStatus = hoursTds.get(2).text();
            // 项目
            String project = hoursTds.get(3).text();
            // 服务团体
            String organization = hoursTds.get(4).text();
            // 日期
            String date = hoursTds.get(5).text();

            volunteerWebHours
                    .setAddWay(addWay)
                    .setDate(date)
                    .setHours(hours)
                    .setHoursStatus(hoursStatus)
                    .setNote(note)
                    .setOrganization(organization)
                    .setProject(project);

            volunteerWebHoursList.add(volunteerWebHours);
        }
        return volunteerWebHoursList;
    }

    @Override
    public VolunteerWebPageInfo getPageInfo(String username, String password) {
        VolunteerWebPageInfo volunteerWebPageInfo = new VolunteerWebPageInfo();

        Document document = volunteerWebService.get(HOURS_INFO_URL, username, password);

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
