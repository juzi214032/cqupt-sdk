package com.juzi.cqupt.sdk.oa.api;

import com.juzi.cqupt.sdk.oa.bean.OaMeeting;

import java.util.List;

/**
 * 通用资讯方法
 *
 * @author Juzi
 * @date 2019/12/28 22:39
 * Blog https://juzibiji.top
 */
public interface OaMeetingService {

    String MEETING_LIST_URL = "/meeting.do?method=queryAllMeeting&curPageNo=";

    String MEETING_DETAIL_URL = "/meeting.do?method=oneMeeting&meetingId=";

    /**
     * 获取列表
     *
     * @param pageOn 页码
     * @return
     */
    List<OaMeeting> getList(int pageOn);

    /**
     * 获取详情
     *
     * @param newsId id
     * @return 资讯
     */
    OaMeeting getDetail(String newsId);
}
