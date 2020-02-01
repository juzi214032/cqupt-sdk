package com.github.juzi214032.cqupt.sdk.volunteer.config;

/**
 * 配置类简单实现
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/8 21:31
 */
public class VolunteerWebSimpleConfig implements VolunteerWebConfig {

    private String domain = "http://www.zycq.org";

    @Override
    public String getDomain() {
        return this.domain;
    }

}
