package com.juzi.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 事务通知
 *
 * @author Juzi
 * @since 2019/12/29 0:56
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class OaTransaction {
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
     * 审稿人
     */
    private String reviewer;
    /**
     * 流程名称
     */
    private String processName;
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
    private List<OaTransactionFile> fileList;
}
