package com.github.juzi214032.cqupt.sdk.library.api;

import com.github.juzi214032.cqupt.sdk.library.bean.recommend.LibRecommendBuy;

import java.util.List;

/**
 * 读者荐购
 *
 * @author Juzi
 * @date 2020/1/22 23:58
 * Blog https://juzibiji.top
 */
public interface LibRecommendBuyService {

    /**
     * 荐购列表
     */
    String RECOMMEND_BUY_LIST_URL = "/asord/asord_hist.php?page=";

    /**
     * 获取荐购列表
     * @param pageOn 页码
     * @return 荐购列表
     */
    List<LibRecommendBuy> getRecommendBuyList(int pageOn);

    /**
     * 获取总页数
     * @return 总页数
     */
    String getTotalPage();

}
