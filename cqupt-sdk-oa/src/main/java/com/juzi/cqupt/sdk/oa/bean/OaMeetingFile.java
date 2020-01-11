package com.juzi.cqupt.sdk.oa.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OA文件
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/12/28 22:43
 */
@Data
@Accessors(chain = true)
public class OaMeetingFile {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 会议id
     * 用于下载文件
     */
    private String meetingId;

    /**
     * 文件编号
     */
    private String fileNo;
}
