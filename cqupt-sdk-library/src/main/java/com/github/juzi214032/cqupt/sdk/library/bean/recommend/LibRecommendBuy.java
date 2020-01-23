package com.github.juzi214032.cqupt.sdk.library.bean.recommend;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 荐购列表
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/23 0:01
 */
@Data
@Accessors(chain = true)
public class LibRecommendBuy {
    /**
     * 书名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String press;
    /**
     * 日期
     */
    private String date;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
}
