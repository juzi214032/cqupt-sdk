package com.github.juzi214032.cqupt.sdk.jwzx.bean.news;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 教务新闻附件
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/11/27 5:12
 */
@Data
@Accessors(chain = true)
public class JwzxNewsFile implements Serializable {

    private static final long serialVersionUID = -780234339132729055L;
    /**
     * 文件id
     * 用于下载文件
     */
    private String fileId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;
}
