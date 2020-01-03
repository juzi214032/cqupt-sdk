package com.juzi.cqupt.sdk.oa.enums;

import lombok.Getter;

/**
 * OA系统资讯类型
 *
 * @author Juzi
 * @since 2019/12/29 0:09
 * Blog https://juzibiji.top
 */
@Getter
public enum NewsType {
    /**
     * 会议通知
     */
    MEETING("会议通知", "/meeting.do?method=queryAllMeeting&curPageNo=", "/meeting.do?method=oneMeeting&meetingId="),
    /**
     * 公示公告
     */
    PUBLICITY("公示公告", "/notify.do?method=queryAllNotify&type=2&curPageNo=", "/notify.do?method=oneNotify&notifyId="),
    /**
     * 党政发文
     */
    GOVERNMENT("党政发文", "/dispatch.do?method=queryPublicDispatch&type=(1,2,3)&curPageNo=", "/dispatch.do?method=oneDispatch&mk=(1,2,3)&dispatchId="),
    /**
     * 群团发文
     */
    ALLIANCE("群团发文", "/dispatch.do?method=queryPublicDispatch&type=(4,5)&curPageNo=", "/dispatch.do?method=oneDispatch&mk=(4,5)&dispatchId="),
    /**
     * 简报信息
     */
    BRIEFING("简报信息", "/customize.do?method=customizeList&type=54325945948646356&curPageNo=", "/customize.do?method=oneCustomize&mk=54325945948646356&customizeId="),
    /**
     * 学术活动
     */
    ACADEMIC("学术活动", "/customize.do?method=customizeList&type=38563376916262312&curPageNo=", "/customize.do?method=oneCustomize&mk=38563376916262312&customizeId="),
    /**
     * 教学动态
     */
    TEACHING("教学动态", "/customize.do?method=customizeList&type=54607411259312969&curPageNo=", "/customize.do?method=oneCustomize&mk=54607411259312969&customizeId="),
    /**
     * 科研工作
     */
    SCIENTIFIC("科研工作", "/customize.do?method=customizeList&type=54607411259313024&curPageNo=", "/customize.do?method=oneCustomize&mk=54607411259313024&customizeId="),
    /**
     * 学生工作
     */
    STUDNET("学生工作", "/customize.do?method=customizeList&type=54607412213904246&curPageNo=", "/customize.do?method=oneCustomize&mk=54607412213904246&customizeId="),
    /**
     * 事务通知
     */
    TRANSACTION("事务通知", "/notify.do?method=queryAllNotify&type=1&curPageNo=", "/notify.do?method=oneNotify&notifyId=");

    /**
     * 资讯模块名称
     */
    private String name;

    /**
     * 列表页uri
     */
    private String listUri;

    /**
     * 详情页uri
     */
    private String detailUri;

    NewsType(String name, String listUri, String detailUri) {
        this.name = name;
        this.listUri = listUri;
        this.detailUri = detailUri;
    }
}
