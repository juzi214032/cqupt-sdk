package com.github.juzi214032.cqupt.sdk.jwzx.bean.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 选课学生信息
 *
 * @author Juzi - https://juzibiji.top
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class JwzxStudentClass extends JwzxStudent implements Serializable {

    private static final long serialVersionUID = -4760759119507526295L;
    /**
     * 选课状态
     */
    private String selectCourseStatus;

}
