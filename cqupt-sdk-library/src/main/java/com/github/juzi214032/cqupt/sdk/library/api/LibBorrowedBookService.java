package com.github.juzi214032.cqupt.sdk.library.api;

import com.github.juzi214032.cqupt.sdk.library.bean.LibBorrowedBookResult;

/**
 * 已借阅图书接口
 *
 * @author Juzi - https://juzibiji.top
 * @date 2019/11/22 11:19
 */
public interface LibBorrowedBookService {

    /**
     * 借阅书籍列表url
     */
    String BORROWED_BOOK_LIST_URL = "/reader/book_lst.php";

    /**
     * 获取借阅图书列表
     *
     * @param username
     * @param password
     * @return
     */
    LibBorrowedBookResult getBorrowedBooks(String username, String password);


}
