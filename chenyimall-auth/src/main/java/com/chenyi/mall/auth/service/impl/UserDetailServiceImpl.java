package com.chenyi.mall.auth.service.impl;

import com.chenyi.mall.auth.entity.LoginUser;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.exception.ChenYiMallException;
import com.chenyi.mall.member.feign.MemberFeignService;
import com.chenyi.mall.member.to.MemberInfo;
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
            throw new ChenYiMallException(ResultEnum.MEMBER_LOGIN_FAIL.getCode(),
                    ResultEnum.MEMBER_LOGIN_FAIL.getMsg());
        }
        return new LoginUser(memberInfo);
    }
}
