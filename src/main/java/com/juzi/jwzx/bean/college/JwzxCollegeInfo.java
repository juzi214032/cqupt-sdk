package com.juzi.jwzx.bean.college;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 学院信息
 *
 * @author Juzi
 * @date 2019/8/3 15:24
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JwzxCollegeInfo {
    
    /**
     * 学院名称
     */
    private String name;
    
    /**
     * 该学院下的所有专业
     */
    private List<JwzxMajorInfo> jwzxMajorInfoList;
}
