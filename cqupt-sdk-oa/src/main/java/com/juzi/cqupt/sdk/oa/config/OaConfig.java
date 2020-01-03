package com.juzi.cqupt.sdk.oa.config;

/**
 * @author Juzi
 * @date 2019/12/28 22:18
 * Blog https://juzibiji.top
 */
public interface OaConfig {

    /**
     * 实际使用域名
     *
     * @return 实际使用域名
     */
    String getDomain();

    /**
     * 获取内网域名
     *
     * @return 内网域名
     */
    String getDomainIn();

    /**
     * 获取外网域名
     *
     * @return 外网域名
     */
    String getDomainOut();

    /**
     * 获取最大重试次数
     *
     * @return 重试次数
     */
    Integer getMaxRetryTimes();

    /**
     * 获取http连接超时时间
     *
     * @return http连接超时时间
     */
    int getTimeout();

}

