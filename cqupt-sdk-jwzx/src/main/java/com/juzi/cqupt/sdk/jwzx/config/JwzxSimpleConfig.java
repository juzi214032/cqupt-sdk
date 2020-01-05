package com.juzi.cqupt.sdk.jwzx.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 配置类实现
 *
 * @author Juzi
 * @since 2019/8/1 19:51
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxSimpleConfig implements JwzxConfig {

    /**
     * 实际使用的域名
     */
    private String domain;

    /**
     * 最大重试次数
     */
    private Integer maxRetryTimes = 5;

    /**
     * 超时时间
     */
    private int timeout;

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
    private String domainIn = "http://jwzx.cqupt.edu.cn";

    /**
     * 外网域名
     */
    private String domainOut = "http://jwzx.cqu.pt";

    /**
     * @param innerNetwork 网络模式（true->内网，false->外网）
     */
    public JwzxSimpleConfig(boolean innerNetwork) {
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
