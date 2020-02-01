package com.github.juzi214032.cqupt.sdk.library.api.impl;

import com.github.juzi214032.cqupt.sdk.library.bean.LibPersonInfo;
import com.github.juzi214032.cqupt.sdk.library.api.LibPersonInfoService;
import com.github.juzi214032.cqupt.sdk.library.api.LibService;
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
