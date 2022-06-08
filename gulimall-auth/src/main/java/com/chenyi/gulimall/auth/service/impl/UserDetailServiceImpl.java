package com.chenyi.gulimall.auth.service.impl;

import com.chenyi.gulimall.auth.entity.LoginUser;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.exception.GuliMallException;
import com.chenyi.gulimall.member.feign.MemberFeignService;
import com.chenyi.gulimall.member.to.MemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenyi
 * @className UserDetailServiceImpl
 * @date 2022/5/15 21:27
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    MemberFeignService memberFeignService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MemberInfo memberInfo = memberFeignService.infoByUserName(userName);
        if (memberInfo == null || memberInfo.getMember() == null) {
            throw new GuliMallException(ResultEnum.MEMBER_LOGIN_FAIL.getCode(),
                    ResultEnum.MEMBER_LOGIN_FAIL.getMsg());
        }
        return new LoginUser(memberInfo);
    }
}
