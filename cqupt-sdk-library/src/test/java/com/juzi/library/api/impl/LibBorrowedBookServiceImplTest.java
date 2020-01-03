package com.juzi.library.api.impl;

import com.juzi.library.api.LibBorrowedBookService;
import com.juzi.library.bean.LibBorrowedBook;
import org.junit.jupiter.api.Test;

import java.util.List;

class LibBorrowedBookServiceImplTest extends LibBaseTest {

    private LibBorrowedBookService libBorrowedBookService = new LibBorrowedBookServiceImpl(this.libService);

    @Test
    void getBorrowedBooks() {
        List<LibBorrowedBook> borrowedBooks = libBorrowedBookService.getBorrowedBooks("2017214032", "jqsmx1731815301");
        System.out.println(borrowedBooks);
    }
}