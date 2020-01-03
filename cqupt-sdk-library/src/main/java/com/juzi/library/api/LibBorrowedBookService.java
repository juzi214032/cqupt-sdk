package com.juzi.library.api;

import com.juzi.library.bean.LibBorrowedBook;

import java.util.List;

/**
 * 已借阅图书接口
 *
 * @author Juzi
 * @date 2019/11/22 11:19
 * Blog https://juzibiji.top
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
    List<LibBorrowedBook> getBorrowedBooks(String username, String password);
}
