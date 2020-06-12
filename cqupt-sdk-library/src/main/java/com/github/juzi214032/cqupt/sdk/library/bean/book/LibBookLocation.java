package com.github.juzi214032.cqupt.sdk.library.bean.book;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 书籍位置
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/16 14:13
 */
@Data
@Accessors(chain = true)
public class LibBookLocation implements Serializable {

    private static final long serialVersionUID = -3671540688315558360L;
    /**
     * 索书号
     */
    private String id;

    /**
     * 条码号
     */
    private String barCode;

    /**
     * 馆藏地
     */
    private String place;

    /**
     * 书籍状态
     */
    private String status;

    /**
     * 应还日期
     */
    private String returnDate;
}
