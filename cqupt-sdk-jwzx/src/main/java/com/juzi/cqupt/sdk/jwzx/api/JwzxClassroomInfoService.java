package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroom;

import java.util.List;

/**
 * 教室接口
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/9/9 20:44
 */
public interface JwzxClassroomInfoService {

    /**
     * 教室信息url
     */
    String CLASSROOM_INFO_URL = "/kebiao/index.php";

    /**
     * 空闲教室查询url
     * 参数
     * - zc 周次（1-20）
     * - xq 星期（1-7）
     * - sd 时段（12、34、zw、56、78、xw）
     */
    String FREE_CLASSROOM_URL = "/jssq/jssqEmptyRoom.php?zc=2&xq=3&sd=12";

    /**
     * 查询空教室
     *
     * @param zc 周次
     * @param xq 星期
     * @param sd 时段
     * @return 空教室列表
     */
    List<String> getFreeClassroomList(String zc, String xq, String sd);

    /**
     * 获取教室信息
     *
     * @return 教室列表
     */
    List<JwzxClassroom> getClassroomList();
}
