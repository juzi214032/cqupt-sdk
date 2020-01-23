package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教材
 *
 * @author Juzi - https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxCourseBook {

    /**
     * 课程号
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 教材名称
     */
    private String courseBookName;

    /**
     * 出版社
     */
    private String press;

    /**
     * 作者
     */
    private String author;

    /**
     * ISBN号
     */
    private String isbn;
}
