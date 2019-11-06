package com.juzi.jwzx.api.impl;

import com.juzi.jwzx.api.JwzxCourseBookService;
import com.juzi.jwzx.api.JwzxService;
import com.juzi.jwzx.bean.JwzxCourseBook;
import com.juzi.jwzx.config.JwzxConfig;
import com.juzi.jwzx.util.RegexUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juzi
 * @date 2019/8/2 19:10
 * Blog https://juzibiji.top
 */
public class JwzxCourseBookServiceImpl implements JwzxCourseBookService {
    
    private JwzxConfig jwzxConfig;
    
    private JwzxService jwzxService;
    
    
    public JwzxCourseBookServiceImpl(JwzxService jwzxService) {
        this.jwzxService = jwzxService;
        this.jwzxConfig = jwzxService.getConfig();
    }
    
    @Override
    public List<JwzxCourseBook> getCourseBooks(String studentId, String password) {
        ArrayList<JwzxCourseBook> jwzxCourseBooks = new ArrayList<>();
        
        Document document = jwzxService.get(COURSE_BOOK_URL, studentId, password);
        
        Elements trs = document.getElementsByTag("tr");
        for (int i = 1; i < trs.size(); i++) {
            String bookInfosHtml = trs.get(i)
                    .getElementsByTag("td").get(2)
                    .toString();
            
            List<String> bookInfoList = RegexUtil.parse("(?<=<br>【.{1,1000}】：)(.*?)(?=<br>|</td>)", bookInfosHtml);
            
            // 置空list中的元素，防止下面取属性时越界
            if (bookInfoList.size() < 3) {
                for (int j = bookInfoList.size(); j < 4; j++) {
                    bookInfoList.add(null);
                }
            }
            
            // 构造对象
            JwzxCourseBook jwzxCourseBook = new JwzxCourseBook();
            jwzxCourseBook
                    .setCourseBookName(bookInfoList.get(0))
                    .setPress(bookInfoList.get(1))
                    .setAuthor(bookInfoList.get(2))
                    .setIsbn(bookInfoList.get(3))
                    .setCourseId(trs.get(i).getElementsByTag("td").get(0).text())
                    .setCourseName(trs.get(i).getElementsByTag("td").get(1).text());
            
            jwzxCourseBooks.add(jwzxCourseBook);
        }
        return jwzxCourseBooks;
    }
}
