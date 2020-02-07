package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 发文文件
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/30 19:02
 */
@Data
@Accessors(chain = true)
public class OaDispatchFile implements Serializable {

    private static final long serialVersionUID = 2547975652662997158L;
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
