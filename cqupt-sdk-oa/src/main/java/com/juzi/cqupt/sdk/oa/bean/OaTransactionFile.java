package com.juzi.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 事务通知附件
 *
 * @author Juzi
 * @since 2019/12/29 20:34
 * Blog https://juzibiji.top
 */
@Data
@Accessors(chain = true)
public class OaTransactionFile {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 通知id
     */
    private String notifyId;

    /**
     * 附件编号
     */
    private String fileNo;

}
