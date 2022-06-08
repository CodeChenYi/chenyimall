package com.chenyi.gulimall.member.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenyi
 * @className SimpleMemberVO
 * @date 2022/5/15 23:23
 */
@Data
public class MemberInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private Member member;

    /**
     * 第三方授权id
     */
    @ApiModelProperty(value = "第三方授权id")
    private String oauthId;


}
