package com.github.juzi214032.cqupt.sdk.library.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 借阅信息结果封装
 * 包含能借阅书籍的本数
 * @author Juzi - https://juzibiji.top
 * @since 2020/2/17 13:42
 */
@Data
@Accessors(chain = true)
public class LibBorrowedBookResult implements Serializable {

    private static final long serialVersionUID = -3426443988406513178L;
    /**
     * 已借图书本数
     */
    private String borrowBookCount;

    /**
     * 共可借图书本数
     */
    private String allBookCount;

    /**
     * 数据
     */
    private List<LibBorrowedBook> data;
}
