package com.github.juzi214032.cqupt.sdk.library.api.impl;

import com.github.juzi214032.cqupt.sdk.library.api.LibBorrowedBookService;
import com.github.juzi214032.cqupt.sdk.library.api.LibService;
import com.github.juzi214032.cqupt.sdk.library.bean.LibBorrowedBook;
import com.github.juzi214032.cqupt.sdk.library.bean.LibBorrowedBookResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/11/22 11:32
 */
public class LibBorrowedBookServiceImpl implements LibBorrowedBookService {

    private LibService libService;

    public LibBorrowedBookServiceImpl(LibService libService) {
        this.libService = libService;
    }

    @Override
    public LibBorrowedBookResult getBorrowedBooks(String username, String password) {
        Document document = this.libService.get(BORROWED_BOOK_LIST_URL, username, password);
        Elements trs = document.select("#mylib_content tr + tr");
        List<LibBorrowedBook> libBorrowedBooks = new ArrayList<>(trs.size());
        for (Element bookTr : trs) {
            Elements bookAttrs = bookTr.select("td");
            // 唯一id
            String marcNo = bookAttrs.select("a").get(0).attr("href").substring(25).trim();
            // 条码号
            String barCode = bookAttrs.get(0).text();
            // 书名
            String title = bookAttrs.select("a").get(0).text();
            // 作者
            String author = bookAttrs.get(1).ownText().substring(2);
            // 借阅日期
            String stringBorrowDate = bookAttrs.get(2).text();
            LocalDate borrowDate = LocalDate.parse(stringBorrowDate, DateTimeFormatter.ISO_LOCAL_DATE);
            // 归还日期
            String stringReturnDate = bookAttrs.get(3).text();
            LocalDate returnDate = LocalDate.parse(stringReturnDate, DateTimeFormatter.ISO_LOCAL_DATE);
            // 馆藏地
            String storePlace = bookAttrs.get(5).text();

            LibBorrowedBook libBorrowedBook = new LibBorrowedBook();
            libBorrowedBook
                    .setMarcNo(marcNo)
                    .setBarCode(barCode)
                    .setTitle(title)
                    .setAuthor(author)
                    .setBorrowDate(borrowDate)
                    .setReturnDate(returnDate)
                    .setStorePlace(storePlace);
            libBorrowedBooks.add(libBorrowedBook);
        }

        // 构造结果
        LibBorrowedBookResult borrowedBookResult = new LibBorrowedBookResult();
        List<String> count = document.select("p[style=margin:10px auto;] b").eachText();
        borrowedBookResult
                .setBorrowBookCount(count.get(0))
                .setAllBookCount(count.get(1))
                .setData(libBorrowedBooks);
        return borrowedBookResult;
    }
}
