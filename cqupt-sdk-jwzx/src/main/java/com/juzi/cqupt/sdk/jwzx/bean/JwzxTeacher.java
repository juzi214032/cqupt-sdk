package com.juzi.cqupt.sdk.jwzx.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 教师信息
 *
 * @author Juzi
 * @since 2019/8/3 14:41
 * Blog https://juzibiji.top
 */
@Data
public class JwzxTeacher {

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
