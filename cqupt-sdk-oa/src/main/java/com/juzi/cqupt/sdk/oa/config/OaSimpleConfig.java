package com.juzi.cqupt.sdk.oa.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Juzi
 * @since 2019/12/28 22:19
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class OaSimpleConfig implements OaConfig {

    /**
     * 实际使用的域名
     */
    private String domain;

    /**
     * 最大重试次数
     */
    private Integer maxRetryTimes = 5;

    /**
     * 内置账号
     */
    private String username;

    /**
     * 内置账号密码
     */
    private String password;

    /**
     * true->内网
     * false->外网
     */
    private boolean innerNetwork;

    /**
     * 内网域名
     */
    private String domainIn = "http://oa.cqupt.edu.cn";

    /**
     * 外网域名
     */
    private String domainOut = "";

    /**
     * 超时时间
     */
    private int timeout = 1000 * 10;

    /**
     * @param innerNetwork 网络模式（true->内网，false->外网）
     */
    public OaSimpleConfig(boolean innerNetwork) {
        this.innerNetwork = innerNetwork;
    }

    @Override
    public Integer getMaxRetryTimes() {
        return this.maxRetryTimes;
    }

    @Override
    public String getDomain() {
        return this.innerNetwork ? domainIn : domainOut;
    }
}

