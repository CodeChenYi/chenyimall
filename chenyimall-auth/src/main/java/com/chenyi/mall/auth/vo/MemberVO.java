package com.chenyi.mall.auth.vo;

import lombok.Data;

/**
 * @author chenyi
 * @className MemberVO
 * @date 2022/9/16 14:59
 */
@Data
public class MemberVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String header;

    /**
     * 积分
     */
    private Integer integration;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 成长值
     */
    private Integer growth;
}
