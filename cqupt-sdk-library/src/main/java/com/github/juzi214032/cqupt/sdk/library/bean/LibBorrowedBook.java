package com.github.juzi214032.cqupt.sdk.library.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 已借阅的图书
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/11/22 11:24
 */
@Data
@Accessors(chain = true)
public class LibBorrowedBook implements Serializable {

    private static final long serialVersionUID = -7225107225057403121L;
    /**
     * 唯一id，用于查询详细信息
     */
    private String marcNo;

    /**
     * 条码号
     */
    private String barCode;

    /**
     * 书名
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 借阅日期
     */
    private LocalDate borrowDate;

    /**
     * 归还日期
     */
    private LocalDate returnDate;

    /**
     * 馆藏地
     */
    private String storePlace;

}
