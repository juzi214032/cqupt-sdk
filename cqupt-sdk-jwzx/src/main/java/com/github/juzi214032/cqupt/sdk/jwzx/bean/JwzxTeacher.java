package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 教师信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/3 14:41
 */
@Data
public class JwzxTeacher implements Serializable {

    private static final long serialVersionUID = 8211256854711201311L;
    /**
     * 教师号
     */
    @JSONField(name = "teaId", serialize = false)
    private String teacherId;

    /**
     * 姓名
     */
    @JSONField(name = "teaName", serialize = false)
    private String name;

    /**
     * 性别
     */
    @JSONField(name = "xb", serialize = false)
    private String gender;

    /**
     * 教研室名称
     */
    @JSONField(name = "jysm", serialize = false)
    private String staffRoomName;

    /**
     * 学院名称
     */
    @JSONField(name = "yxm", serialize = false)
    private String collegeName;

    /**
     * 职称
     */
    @JSONField(name = "zc", serialize = false)
    private String teacherTitle;
}
