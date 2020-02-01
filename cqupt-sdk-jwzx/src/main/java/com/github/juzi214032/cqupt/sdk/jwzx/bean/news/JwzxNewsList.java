package com.github.juzi214032.cqupt.sdk.jwzx.bean.news;

import lombok.Data;

import java.io.Serializable;

/**
 * 新闻列表
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/11/27 0:42
 */
@Data
public class JwzxNewsList implements Serializable {

    private static final long serialVersionUID = -6586574574534680128L;
    /**
     * 文章总数
     */
    private String totalCount;

    /**
     * 文章id
     */
    private String fileId;

    /**
     * 未知id
     */
    private String dirId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 未知id
     */
    private String pubId;

    /**
     * 发布时间
     */
    private String pubTime;

    /**
     * 发布ip
     */
    private String pubIp;

    /**
     * 阅读量
     */
    private String readCount;

    /**
     * 发布人
     */
    private String teaName;

    /**
     * 发布于几天前
     */
    private String days;
}
