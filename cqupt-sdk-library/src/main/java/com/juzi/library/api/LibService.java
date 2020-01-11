package com.juzi.library.api;

import com.juzi.library.config.LibConfig;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/16 14:22
 */
public interface LibService {

    /**
     * 验证码链接
     */
    String AUTH_CODE_IMG_URL = "/reader/captcha.php";

    /**
     * 登录页面地址
     */
    String LOGIN_PAGE_URL = "/reader/login.php";

    /**
     * 登录api接口
     */
    String LOGIN_API_URL = "/reader/redr_verify.php";

    /**
     * 姓名校验页url
     */
    String CHECK_NAME_PAGE_URL = "/reader/redr_con.php";

    /**
     * 姓名校验接口url
     */
    String CHECK_NAME_API_URL = "/reader/redr_con_result.php";

    /**
     * 返回配置对象
     *
     * @return 配置对象
     */
    LibConfig getConfig();

    /**
     * 登录
     *
     * @param username 学号
     * @param password 密码/初始为学号
     * @return 认证后的cookie
     * @throws IOException
     */
    Map<String, String> login(String username, String password);

    /**
     * 登录
     *
     * @param username    学号
     * @param password    密码/初始为学号
     * @param name        真实姓名（第一次登录需要校验）
     * @param forceUpdate 是否强制更新cookie
     * @return 认证后的cookie
     */
    Map<String, String> login(String username, String password, String name, boolean forceUpdate);

    /**
     * @param uri      地址
     * @param username 账号
     * @param password 密码
     * @return Jsoup文档对象
     */
    Document get(String uri, String username, String password);
}
