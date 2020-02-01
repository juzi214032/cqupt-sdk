package com.github.juzi214032.cqupt.sdk.jwzx.bean.student;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 学生基础信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 19:02
 */
@Data
@ToString
@Accessors(chain = true)
public class JwzxStudent implements Serializable {

    private static final long serialVersionUID = 7787778583256062426L;
    /**
     * 学号
     */
    @JSONField(name = "xh", serialize = false)
    private String studentId;

    /**
     * 姓名
     */
    @JSONField(name = "xm", serialize = false)
    private String name;

    /**
     * 姓名英文
     */
    @JSONField(name = "xmEn", serialize = false)
    private String nameEn;

    /**
     * 性别
     */
    @JSONField(name = "xb", serialize = false)
    private String gender;

    /**
     * 班级
     */
    @JSONField(name = "bj", serialize = false)
    private String clazz;

    /**
     * 专业号
     */
    @JSONField(name = "zyh", serialize = false)
    private String majorId;

    /**
     * 专业名
     */
    @JSONField(name = "zym", serialize = false)
    private String majorName;

    /**
     * 专业名英文
     */
    @JSONField(name = "zymEn", serialize = false)
    private String majorNameEn;

    /**
     * 学院号
     */
    @JSONField(name = "yxh", serialize = false)
    private String collegeId;

    /**
     * 学院名
     */
    @JSONField(name = "yxm", serialize = false)
    private String collegeName;

    /**
     * 学院名英文
     */
    @JSONField(name = "yxmen", serialize = false)
    private String collegeNameEn;

    /**
     * 年级
     */
    @JSONField(name = "nj", serialize = false)
    private String grade;

    /**
     * 生日
     */
    @JSONField(name = "csrq", serialize = false)
    private String birthday;

    /**
     * 学籍状态
     */
    @JSONField(name = "xjzt", serialize = false)
    private String studyStatus;

    /**
     * 入学日期
     */
    @JSONField(name = "rxrq", serialize = false)
    private String joinStudyDate;

    /**
     * 学制
     */
    @JSONField(name = "xz", serialize = false)
    private String studyLength;

    /**
     * 民族
     */
    @JSONField(name = "mz", serialize = false)
    private String national;
}
