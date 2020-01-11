package com.juzi.library.api.impl;

import com.juzi.library.api.LibPersonInfoService;
import com.juzi.library.api.LibService;
import com.juzi.library.bean.LibPersonInfo;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/17 22:27
 */
public class LibPersonInfoServiceImpl implements LibPersonInfoService {

    private LibService libService;

    public LibPersonInfoServiceImpl(LibService libService) {
        this.libService = libService;
    }

    @Override
    public LibPersonInfo getPersonInfo(String username, String password) {
        Document personInfoDocument = libService.get(PERSONAL_INFO_PAGE_URL, username, password);
        Elements infoTdElements = personInfoDocument.getElementsByTag("table").get(0).getElementsByTag("td");
        return new LibPersonInfo().parse(infoTdElements);
    }
}
