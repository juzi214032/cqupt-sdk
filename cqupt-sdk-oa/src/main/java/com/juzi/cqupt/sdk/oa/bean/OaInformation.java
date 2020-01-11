package com.juzi.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 通用信息
 * <p>
 * 包含：
 * 简报信息
 * 学术活动
 * 教学动态
 * 科研工作
 * 学生工作
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/11 12:30
 */
@Data
@Accessors(chain = true)
public class OaInformation {
    /**
     * 信息id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 发布人
     */
    private String releasePerson;
    /**
     * 发布部门
     */
    private String releaseDepartment;
    /**
     * 审稿人
     */
    private String reviewer;
    /**
     * 浏览量
     */
    private String views;
    /**
     * 附件
     */
    private List<OaInformationFile> files;
}
