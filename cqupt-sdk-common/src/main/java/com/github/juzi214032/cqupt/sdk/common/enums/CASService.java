package com.github.juzi214032.cqupt.sdk.common.enums;

/**
 * CAS单点登录服务枚举
 *
 * @author Juzi - https://juzibiji.top
 * @date 2019/12/27 21:02
 */
public enum CASService {

    /**
     * 教务在线
     */
    JWZX("http://jwc.cqupt.edu.cn/tysfrz/index.php"),
    /**
     * 图书馆
     */
    LIBRARY("http://202.202.43.84/reader/hwthau.php");

    private String url;

    CASService(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
