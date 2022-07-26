package com.chenyi.mall.auth.service;

import com.chenyi.mall.member.to.MemberInfo;

/**
 * @author chenyi
 * @className AuthTokenService
 * @date 2022/5/16 20:32
 */
public interface AuthTokenService {
    /**
     * 登录获取用户信息
     * @param userName
     * @param password
     * @return
     */
    String infoByUserName(String userName, String password);

    /**
     * 验证token，登录信息
     * @param token
     * @return
     */
    MemberInfo verifyToken(String token);
}
