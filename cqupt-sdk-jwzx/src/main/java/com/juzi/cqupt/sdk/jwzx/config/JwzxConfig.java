package com.juzi.cqupt.sdk.jwzx.config;

/**
 * 教务在线相关配置
 *
 * @author Juzi
 */
public interface JwzxConfig {

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
     * @return http请求超时时间
     */
    int getTimeout();

    /**
     * 内置账号
     * 用户获取学生照片时使用
     * 因为教务在线显示他人照片需要一个已登录的cookie
     *
     * @return
     */
    String getUsername();

    /**
     * 内置账号密码
     *
     * @return
     */
    String getPassword();
}
