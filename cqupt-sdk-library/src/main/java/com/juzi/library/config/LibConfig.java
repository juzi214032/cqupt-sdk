package com.juzi.library.config;

/**
 * 图书馆网站相关配置
 *
 * @author Juzi
 */
public interface LibConfig {

    /**
     * @return 实际使用域名
     */
    String getDomain();

    /**
     * @return 内网域名
     */
    String getDomainIn();

    /**
     * @return 外网域名
     */
    String getDomainOut();

    /**
     * 获取最大重试次数
     *
     * @return 重试次数
     */
    Integer getMaxRetryTimes();
}
