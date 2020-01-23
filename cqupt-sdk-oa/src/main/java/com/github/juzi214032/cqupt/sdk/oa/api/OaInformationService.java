package com.github.juzi214032.cqupt.sdk.oa.api;

import com.github.juzi214032.cqupt.sdk.oa.bean.OaInformation;
import com.github.juzi214032.cqupt.sdk.oa.enums.NewsType;

import java.util.List;
import java.util.regex.Pattern;

/**
 * OA信息
 * <p>
 * 包含：
 * 简报信息
 * 学术活动
 * 教学动态
 * 科研工作
 * 学生工作
 *
 * @author Juzi
 * @date 2020/1/11 12:30
 * Blog https://juzibiji.top
 */
public interface OaInformationService {

    /**
     * 获取文章id的正则
     */
    Pattern INFORMATION_ID_PATTERN = Pattern.compile("(?:customizeId=)(\\d*)$");
    /**
     * 获取文件id的正则
     */
    Pattern FILE_ID_PATTERN = Pattern.compile("(?:fjId=)(\\d*)$");

    /**
     * 获取简报列表
     *
     * @param newsType 信息类型
     * @param pageOn   页码
     * @return 简报列表
     */
    List<OaInformation> getList(NewsType newsType, int pageOn);

    /**
     * 获取简报详情
     *
     * @param newsType 信息类型
     * @param id       简报id
     * @return 简报详情
     */
    OaInformation getDetail(NewsType newsType, String id);

    /**
     * 获取总页数
     *
     * @param newsType 信息类型
     * @return 总页码
     */
    int getTotalPage(NewsType newsType);
}
