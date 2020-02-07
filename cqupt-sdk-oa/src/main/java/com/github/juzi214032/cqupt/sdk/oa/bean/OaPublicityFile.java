package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公示公告文件
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/30 19:32
 */
@Data
@Accessors(chain = true)
public class OaPublicityFile implements Serializable {

    private static final long serialVersionUID = 697325128971912793L;
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 公示公告id
     */
    private String publicityId;

    /**
     * 文件编号
     */
    private String fileNo;
}
