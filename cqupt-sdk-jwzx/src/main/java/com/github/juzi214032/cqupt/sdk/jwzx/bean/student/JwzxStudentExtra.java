package com.github.juzi214032.cqupt.sdk.jwzx.bean.student;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 学生额外信息
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/1 19:14
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JwzxStudentExtra extends JwzxStudent implements Serializable {

    private static final long serialVersionUID = -2404542371079104266L;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 统一认证码
     */
    private String unionId;

}
