package com.juzi.volunteer.web.api.impl;

import com.juzi.volunteer.web.api.VolunteerWebPersonalInfoService;
import com.juzi.volunteer.web.api.VolunteerWebService;
import com.juzi.volunteer.web.bean.VolunteerWebPersonalInfo;
import com.juzi.volunteer.web.config.VolunteerWebConfig;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Juzi
 * @since 2019/8/9 13:32
 * Blog https://juzibiji.top
 */
public class VolunteerWebPersonalInfoServiceImpl implements VolunteerWebPersonalInfoService {

    public static final Pattern PERSONAL_INFO_PATTERN = Pattern.compile("(?<=(欢迎您回来，)|(志愿者编号：))(.*?)(?=，)");
    private VolunteerWebConfig volunteerWebConfig;
    private VolunteerWebService volunteerWebService;

    public VolunteerWebPersonalInfoServiceImpl(VolunteerWebService volunteerWebService) {
        this.volunteerWebService = volunteerWebService;
        this.volunteerWebConfig = volunteerWebService.getConfig();
    }

    @Override
    public VolunteerWebPersonalInfo getPersonalInfo(String username, String password) {
        Document document = volunteerWebService.get(PERSONAL_INFO_URL, username, password);

        String personalSimpleInfo = document.getElementsByTag("td").get(0).ownText();
        Matcher matcher = PERSONAL_INFO_PATTERN.matcher(personalSimpleInfo);

        matcher.find();
        // 姓名
        String name = matcher.group();

        matcher.find();
        // 志愿者编号
        String voulunteerId = matcher.group();

        // 时长信息
        String time = document.getElementsByAttributeValueContaining("href", "/app/user/hour.php").get(2).ownText();
        // 加入志愿团体个数
        String organizationCount = document.getElementsByAttributeValueContaining("href", "/app/org/org.my.php").get(3).ownText();
        // 参与项目个数
        String projectCount = document.getElementsByAttributeValueContaining("href", "/app/opp/opp.my.php").get(2).ownText();

        VolunteerWebPersonalInfo volunteerWebPersonalInfo = new VolunteerWebPersonalInfo();
        volunteerWebPersonalInfo
                .setName(name)
                .setTime(time)
                .setVolunteerId(voulunteerId)
                .setOrganizationCount(Integer.parseInt(organizationCount))
                .setProjectCount(Integer.parseInt(projectCount));

        return volunteerWebPersonalInfo;
    }
}
