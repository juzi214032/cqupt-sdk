package com.juzi.library.exception;

/**
 * 用户第一次登录，未做姓名校验
 *
 * @author Juzi
 * @since 2019/11/22 17:16
 * Blog https://juzibiji.top
 */
public class LibAccountUncheckException extends LibException {
    public LibAccountUncheckException() {
        super("用户第一次登录，请先进行姓名校验");
    }
}
