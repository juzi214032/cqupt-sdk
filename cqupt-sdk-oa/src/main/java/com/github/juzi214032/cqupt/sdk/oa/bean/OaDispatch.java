package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 发文
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/29 20:50
 */
@Data
@Accessors(chain = true)
public class OaDispatch {

    /**
     * 唯一id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
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
     * 流程名称
     */
    private String processName;
    /**
     * 文号
     */
    private String contentNo;
    /**
     * 共印份数
     */
    private String totalCopy;
    /**
     * 缓急
     */
    private String priorities;
    /**
     * 密级
     */
    private String confidentialityLevel;
    /**
     * 主送
     */
    private String mainSending;
    /**
     * 抄送
     */
    private String copySending;
    /**
     * 审核人
     */
    private String verifier;
    /**
     * 审稿人
     */
    private String reviewer;
    /**
     * 签发人
     */
    private String issuePerson;
    /**
     * 会签
     */
    private String countersign;
    /**
     * 文件列表
     */
    private List<OaDispatchFile> fileList;
}
