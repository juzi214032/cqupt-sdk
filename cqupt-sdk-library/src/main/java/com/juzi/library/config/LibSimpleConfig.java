package com.juzi.library.config;

import lombok.Data;

/**
 * 配置类实现
 *
 * @author Juzi
 * @since 2019/8/1 19:51
 * Blog https://juzibiji.top
 */
@Data
public class LibSimpleConfig implements LibConfig {

    /**
     * 实际使用的域名
     */
    private String domain;

    /**
     * 内网域名
     */
    private String domainIn = "http://202.202.43.84";

    /**
     * 外网域名
     */
    private String domainOut = "http://202.202.43.84.cqu.pt";

    /**
     * 是否内网
     */
    private boolean isInnerNetwork;

    /**
     * 最大重试次数
     */
    private Integer maxRetryTimes = 5;

    /**
     * @param isInnerNetwork 网络模式（true->内网，false->外网）
     */
    public LibSimpleConfig(boolean isInnerNetwork) {
        this.isInnerNetwork = isInnerNetwork;
    }

    @Override
    public String getDomain() {
        return this.isInnerNetwork ? domainIn : domainOut;
    }

}
