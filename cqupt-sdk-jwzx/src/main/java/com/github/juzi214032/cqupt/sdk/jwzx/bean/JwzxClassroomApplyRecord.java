package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 教室申请记录
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/3 18:35
 */
@Data
@Accessors(chain = true)
public class JwzxClassroomApplyRecord implements Serializable {
    private static final long serialVersionUID = -1361040196844170770L;
    /**
     * 学期
     */
    private String term;
    /**
     * 申请时间（提交申请的时间）
     */
    private String applyTime;
    /**
     * 借用时间（申请的时间）
     */
    private String borrowTime;
    /**
     * 申请时填写的标题
     */
    private String title;
    /**
     * 人数
     */
    private String peopleCount;
    /**
     * 负责人信息
     */
    private String leader;
    /**
     * 申请的教室
     */
    private String classroom;
    /**
     * 审核状态
     */
    private String result;

}
