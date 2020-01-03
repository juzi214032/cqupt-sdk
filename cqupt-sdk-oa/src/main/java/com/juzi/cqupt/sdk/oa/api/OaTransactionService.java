package com.juzi.cqupt.sdk.oa.api;

import com.juzi.cqupt.sdk.oa.bean.OaTransaction;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 事务通知
 *
 * @author Juzi
 * @date 2019/12/29 0:54
 * Blog https://juzibiji.top
 */
public interface OaTransactionService {

    /**
     * 列表页uri
     */
    String TRANSACTION_LIST_URL = "/notify.do?method=queryAllNotify&type=1&curPageNo=";

    /**
     * 详情页uri
     */
    String TRANSACTION_DETAIL_URL = "/notify.do?method=oneNotify&notifyId=";

    /**
     * 提取页码总数正则
     */
    Pattern TOTAL_PAGE_PATTERN = Pattern.compile("(?<=ShowoPage\\().*?(?=,)");

    /**
     * 获取事务通知列表
     *
     * @param pageOn
     * @return
     */
    List<OaTransaction> getList(int pageOn);

    /**
     * 获取事务通知详情
     *
     * @param id 事务通知id
     * @return
     */
    OaTransaction getDetail(String id);

    /**
     * 获取分页信息
     *
     * @return 一共有多少页
     */
    int getTotalPage();
}
