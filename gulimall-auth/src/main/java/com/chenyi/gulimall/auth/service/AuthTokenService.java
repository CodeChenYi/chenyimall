package com.chenyi.gulimall.auth.service;

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
}
