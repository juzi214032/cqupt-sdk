package com.github.juzi214032.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 事务通知附件
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/29 20:34
 */
@Data
@Accessors(chain = true)
public class OaTransactionFile implements Serializable {

    private static final long serialVersionUID = -8894372955896771073L;
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
