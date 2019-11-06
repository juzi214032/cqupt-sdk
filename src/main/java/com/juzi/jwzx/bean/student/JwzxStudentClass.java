package com.juzi.jwzx.bean.student;

import lombok.Data;
import lombok.ToString;

/**
 * 选课学生信息
 *
 * @author Juzi
 */
@Data
@ToString(callSuper = true)
public class JwzxStudentClass extends JwzxStudent {
    
    /**
     * 选课状态
     */
    private String selectCourseStatus;
    
}
