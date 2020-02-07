package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 公示公告
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/30 19:31
 */
@Data
@Accessors(chain = true)
public class OaPublicity implements Serializable {

    private static final long serialVersionUID = 3686872782173289360L;
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
     * 审稿人
     */
    private String reviewer;

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
    private List<OaPublicityFile> fileList;
}
