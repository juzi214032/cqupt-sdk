package com.juzi.volunteer.web.config;

/**
 * 配置类简单实现
 *
 * @author Juzi
 * @since 2019/8/8 21:31
 * Blog https://juzibiji.top
 */
public class VolunteerWebSimpleConfig implements VolunteerWebConfig {

    private String domain = "http://www.zycq.org";

    @Override
    public String getDomain() {
        return this.domain;
    }

}
