package com.github.juzi214032.cqupt.sdk.volunteer.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 项目列表分页信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/9 22:22
 */
@Data
@Accessors(chain = true)
public class VolunteerWebPageInfo implements Serializable {

    private static final long serialVersionUID = -1679867731602901270L;
    /**
     * 总页数
     */
    private Integer pageNumTotal;

    /**
     * 总共有多少条记录
     */
    private Integer recordNumTotal;
}
