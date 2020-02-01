package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 教师
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/3 16:15
 */
@Data
@Accessors(chain = true)
public class JwzxClassroom implements Serializable {

    private static final long serialVersionUID = 5800552492847979158L;
    /**
     * 教室名称
     */
    private String name;

    /**
     * 容纳人数
     */
    private String contain;

    /**
     * 教室分类
     */
    private String type;

    /**
     * 教室所属学院
     */
    private String college;

}
