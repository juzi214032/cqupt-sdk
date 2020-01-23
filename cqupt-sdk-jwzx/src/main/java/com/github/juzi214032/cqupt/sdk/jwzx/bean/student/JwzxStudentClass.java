package com.github.juzi214032.cqupt.sdk.jwzx.bean.student;

import lombok.Data;
import lombok.ToString;

/**
 * 选课学生信息
 *
 * @author Juzi - https://juzibiji.top
 */
@Data
@ToString(callSuper = true)
public class JwzxStudentClass extends JwzxStudent {

    /**
     * 选课状态
     */
    private String selectCourseStatus;

}
