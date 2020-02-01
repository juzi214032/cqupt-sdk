package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 时间
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/12 12:10
 */
@Data
@Accessors(chain = true)
public class JwzxDate implements Serializable {

    private static final long serialVersionUID = 5860599622022147340L;
    /**
     * 学年
     */
    private String studyYear;

    /**
     * 学期（1/2）
     */
    private String term;

    /**
     * 第几周
     */
    private Integer week;

    /**
     * 星期几(1-7)
     */
    private Integer day;

}
