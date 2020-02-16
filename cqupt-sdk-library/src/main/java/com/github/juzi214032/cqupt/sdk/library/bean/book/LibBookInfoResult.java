package com.github.juzi214032.cqupt.sdk.library.bean.book;

import lombok.Data;

import java.util.List;

/**
 * 图书搜索结果封装
 * 包含分页信息
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/16 14:23
 */
@Data
public class LibBookInfoResult {

    /**
     * 总页数
     */
    private Integer pageTotal;

    /**
     * 数据
     */
    private List<LibBookInfo> data;

}
