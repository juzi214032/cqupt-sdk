package com.juzi.library.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 已借阅的图书
 *
 * @author Juzi
 * @since 2019/11/22 11:24
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class LibBorrowedBook {

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
