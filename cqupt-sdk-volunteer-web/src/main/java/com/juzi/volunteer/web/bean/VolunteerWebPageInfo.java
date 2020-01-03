package com.juzi.volunteer.web.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目列表分页信息
 *
 * @author Juzi
 * @since 2019/8/9 22:22
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class VolunteerWebPageInfo {

    /**
     * 总页数
     */
    private Integer pageNumTotal;

    /**
     * 总共有多少条记录
     */
    private Integer recordNumTotal;
}
