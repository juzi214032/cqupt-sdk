package com.juzi.jwzx.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教务在线新闻
 *
 * @author Juzi
 * @date 2019/9/6 19:18
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxNews {
    
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
     * 内容（保留基本格式：缩进）
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
}
