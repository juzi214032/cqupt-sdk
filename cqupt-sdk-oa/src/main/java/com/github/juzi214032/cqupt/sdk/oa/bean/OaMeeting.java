package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 会议通知
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/28 22:39
 */
@Data
@Accessors(chain = true)
public class OaMeeting {

    /**
     * 唯一id
     */
    private String id;

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
     * 开会时间
     */
    private String meetingTime;

    /**
     * 开会地点
     */
    private String meetingPlace;

    /**
     * 审稿人
     */
    private String reviewer;

    /**
     * 主持人
     */
    private String presenter;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件列表
     */
    private List<OaMeetingFile> fileList;
}
