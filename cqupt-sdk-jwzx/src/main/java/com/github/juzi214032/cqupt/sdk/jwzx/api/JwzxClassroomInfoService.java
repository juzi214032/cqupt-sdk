package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxClassroom;

import java.util.List;
import java.util.regex.Pattern;

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
    String FREE_CLASSROOM_URL = "/jssq/jssqEmptyRoom.php";

    /**
     * 空教室查询新接口
     */
    String FREE_CLASSROOM_API = "/kebiao/emptyRoomSearch.php";

    /**
     * 空教室提取正则
     */
    Pattern FREE_CLASSROOM_REGEX = Pattern.compile("^(\\d+) \\((\\d+)\\)");

    /**
     * 查询空教室
     * 已弃用，请使用下面的新接口
     *
     * @param zc 周次
     * @param xq 星期1-7
     * @param sd 时段
     * @return 空教室列表
     */
    @Deprecated
    List<String> getFreeClassroomList(String zc, String xq, String sd);

    /**
     * 查询空教室
     * 新接口
     *
     * @param zc 第几周
     * @param xq 星期几1-7
     * @param sd 时段（2/4/6/8/10/12）
     * @return 空教室列表
     */
    List<JwzxClassroom> getFreeClassroomList(String zc, String xq, String[] sd);

    /**
     * 获取教室信息
     *
     * @return 教室列表
     */
    List<JwzxClassroom> getClassroomList();
}
