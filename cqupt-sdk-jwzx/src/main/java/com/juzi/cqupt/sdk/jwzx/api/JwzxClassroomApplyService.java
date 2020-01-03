package com.juzi.cqupt.sdk.jwzx.api;

import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroomApplyInfo;
import com.juzi.cqupt.sdk.jwzx.bean.JwzxClassroomApplyRecord;
import com.juzi.cqupt.sdk.jwzx.exception.JwzxClassroomApplyException;

import java.util.List;
import java.util.Map;

/**
 * 教室申请
 *
 * @author Juzi
 * @date 2020/1/3 16:19
 * Blog https://juzibiji.top
 */
public interface JwzxClassroomApplyService {

    /**
     * 创建申请页面html代码的uri
     */
    String CREATE_APPLY_URL = "/jssq/jssq.php";

    /**
     * 查询可申请教室uri
     */
    String AVAILABLE_CLASSROOM_URL = "/jssq/jssqEmptyRoom.php";

    /**
     * 提交教室申请api
     */
    String APPLY_CLASSROOM_API = "/jssq/post.php";

    /**
     * 教室申请记录url
     */
    String APPLY_CLASSROOM_RECORD_URL = "/jssq/jssqHistory.php";

    /**
     * 获取申请教室所需的固定信息
     *
     * @param username 账号
     * @param password 密码
     * @return 申请教室时的固定信息
     */
    JwzxClassroomApplyInfo getClassroomApplyInfo(String username, String password);

    /**
     * 查询可申请的教室
     *
     * @param username 账号
     * @param password 密码
     * @param week     第几周
     * @param weekNo   星期几
     * @param courseNo 第几节
     * @return
     */
    List<Map<String, String>> getAvailableClassroom(String username, String password, String week, String weekNo, String courseNo);

    /**
     * 获取历史申请记录
     *
     * @param username 账号
     * @param password 密码
     * @return 申请记录
     */
    List<JwzxClassroomApplyRecord> getApplyRecords(String username, String password);

    /**
     * 申请教室
     *
     * @param username  账号
     * @param password  密码
     * @param type      申请类型（原因）
     * @param title     标题
     * @param college   学院/单位
     * @param leader    学院/单位负责人
     * @param name      申请人姓名
     * @param phone     申请人电话
     * @param week      第几周
     * @param weekNo    星期几
     * @param courseNo  第几节
     * @param classroom 教室号
     * @return
     */
    boolean postClassroomAppply(
            String username,
            String password,
            String type,
            String title,
            String college,
            String leader,
            String name,
            String phone,
            String week,
            String weekNo,
            String courseNo,
            String classroom) throws JwzxClassroomApplyException;
}
