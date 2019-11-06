package com.juzi.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师
 *
 * @author Juzi
 * @date 2019/8/3 16:15
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class JwzxClassroom {
    
    /**
     * 教室名称
     */
    private String name;
    
    /**
     * 容纳人数
     */
    private String contain;
    
    /**
     * 教室分类
     */
    private String type;
    
    /**
     * 教室所属学院
     */
    private String college;
    
}
