package com.github.juzi214032.cqupt.sdk.library.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.select.Elements;

import java.io.Serializable;

/**
 * 图书馆页面获取的个人信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/16 14:04
 */
@Data
@Accessors(chain = true)
public class LibPersonInfo implements Serializable {

    private static final long serialVersionUID = 376109881123455306L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 证件号（学号）
     */
    private String idNo;

    /**
     * 条码号（统一认证码）
     */
    private String barNo;

    /**
     * 失效日期
     */
    private String expiryDate;

    /**
     * 办证日期
     */
    private String checkDate;

    /**
     * 生效日期
     */
    private String effectiveDate;

    /**
     * 最大可借图书数量
     */
    private Integer maxBorrowBook;

    /**
     * 最大可预约图书数量
     */
    private Integer maxOrderBook;

    /**
     * 最大可委托图书数量
     */
    private Integer maxDelegateBook;

    /**
     * 读者类型
     */
    private String readerType;

    /**
     * 借阅等级
     */
    private String borrowLevel;

    /**
     * 累计借书次数
     */
    private String borrowBookCount;

    /**
     * 违章次数
     */
    private String violationNum;

    /**
     * 欠款金额
     */
    private String amountsOwed;

    /**
     * 系别
     */
    private String department;

    /**
     * 绑定的email
     */
    private String email;

    /**
     * 身份证号码（带X去敏的数据）
     */
    private String identityNo;

    /**
     * 职称
     */
    private String jobTitle;

    /**
     * 职位
     */
    private String jobName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 手机
     */
    private String phone;

    /**
     * 文化程度
     */
    private String educationLevel;

    /**
     * 押金
     */
    private String deposit;

    /**
     * 手续费
     */
    private String poundage;

    public LibPersonInfo parse(Elements infoTdElements) {
        name = infoTdElements.get(1).ownText();
        idNo = infoTdElements.get(2).ownText();
        barNo = infoTdElements.get(3).ownText();
        expiryDate = infoTdElements.get(4).ownText();
        checkDate = infoTdElements.get(5).ownText();
        effectiveDate = infoTdElements.get(6).ownText();
        maxBorrowBook = Integer.parseInt(infoTdElements.get(7).ownText());
        maxOrderBook = Integer.parseInt(infoTdElements.get(8).ownText());
        maxDelegateBook = Integer.parseInt(infoTdElements.get(9).ownText());
        readerType = infoTdElements.get(10).ownText();
        borrowLevel = infoTdElements.get(11).ownText();
        borrowBookCount = infoTdElements.get(12).ownText();
        violationNum = infoTdElements.get(13).ownText();
        amountsOwed = infoTdElements.get(14).ownText();
        department = infoTdElements.get(15).ownText();
        email = infoTdElements.get(16).ownText();
        identityNo = infoTdElements.get(17).ownText();
        jobTitle = infoTdElements.get(18).ownText();
        jobName = infoTdElements.get(19).ownText();
        gender = infoTdElements.get(20).ownText();
        postcode = infoTdElements.get(21).ownText();
        telephone = infoTdElements.get(22).ownText();
        phone = infoTdElements.get(23).ownText();
        educationLevel = infoTdElements.get(24).ownText();
        deposit = infoTdElements.get(25).ownText();
        poundage = infoTdElements.get(26).ownText();
        return this;
    }
}
