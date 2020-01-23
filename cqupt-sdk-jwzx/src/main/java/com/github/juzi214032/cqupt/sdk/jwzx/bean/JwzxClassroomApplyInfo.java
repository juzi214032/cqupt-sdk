package com.github.juzi214032.cqupt.sdk.jwzx.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 教室创建申请固定信息
 * 有一些下拉表单是教务在线固定的值，不可随意填写
 * 这是从教务在线实时获取的固定信息
 * <p>
 * 所有返回信息都是键值对
 * key->显示名称
 * value->表单应提交的数据值
 *
 * @author Juzi - https://juzibiji.top
 * @since 2020/1/3 16:25
 */
@Data
@Accessors(chain = true)
public class JwzxClassroomApplyInfo {

    /**
     * 申请原因（就是申请类型）
     */
    private List<Map<String, String>> allTypes;

    /**
     * 可选择的周次（第几周）
     */
    private List<Map<String, String>> week;

    /**
     * 可选择的星期几
     */
    private List<Map<String, String>> weekNo;

    /**
     * 可选择的节数
     */
    private List<Map<String, String>> courseNo;
}
