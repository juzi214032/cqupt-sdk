package com.juzi.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 党政发文文件
 *
 * @author Juzi
 * @since 2019/12/30 19:02
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class OaGovernmentFile {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文章id
     */
    private String governmentId;

    /**
     * 文件编号
     */
    private String fileNo;
}
