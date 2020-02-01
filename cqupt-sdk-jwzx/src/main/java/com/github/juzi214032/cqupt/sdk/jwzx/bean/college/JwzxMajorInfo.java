package com.github.juzi214032.cqupt.sdk.jwzx.bean.college;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 专业
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/3 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwzxMajorInfo implements Serializable {

    private static final long serialVersionUID = -4167176070014018903L;
    /**
     * 专业号
     */
    private String majorId;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 该专业下的每个班级信息
     */
    private Map<String, List<String>> clazzMap;
}
