package com.chenyi.mall.api.member.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author chenyi
 * @className SimpleMemberVO
 * @date 2022/5/15 23:23
 */
@ToString
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public Long memberId() {
        return member.getId();
    }

    public String memberUserName() {
        return member.getUsername();
    }

    public String memberNickName() {
        return member.getNickname();
    }
}
