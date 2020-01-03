package com.juzi.cqupt.sdk.jwzx.api.impl;

import com.juzi.cqupt.sdk.jwzx.api.JwzxCourseBookService;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxCourseBook;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JwzxCourseBookServiceImplTest extends JwzxBaseTest {

    private JwzxCourseBookService jwzxCourseBookService = new JwzxCourseBookServiceImpl(jwzxService);

    @Test
    void getCourseBooks() {
        List<JwzxCourseBook> courseBooks = jwzxCourseBookService.getCourseBooks("1655633", "jqsmx1731815301");
        assertNotEquals(0, courseBooks.size());
        courseBooks.forEach(System.out::println);
    }
}
