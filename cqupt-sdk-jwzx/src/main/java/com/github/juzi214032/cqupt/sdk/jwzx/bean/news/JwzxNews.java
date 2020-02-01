package com.github.juzi214032.cqupt.sdk.jwzx.bean.news;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 教务在线新闻
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/6 19:18
 */
@Data
@Accessors(chain = true)
public class JwzxNews implements Serializable {

    private static final long serialVersionUID = 5561257116730382318L;
    /**
     * 新闻id
     */
    @JSONField(name = "fileId", serialize = false)
    private Integer newsId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容（HTML格式）
     */
    private String content;

    /**
     * 发布时间
     */
    @JSONField(name = "pubTime", serialize = false)
    private String time;

    /**
     * 发布人
     */
    @JSONField(name = "teaName", serialize = false)
    private String author;

    /**
     * 阅读量
     */
    @JSONField(name = "readCount", serialize = false)
    private Integer views;

    /**
     * 发布于几天前
     */
    private Integer days;

    private List<JwzxNewsFile> fileList;
}
