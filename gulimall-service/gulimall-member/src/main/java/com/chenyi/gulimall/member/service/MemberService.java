package com.chenyi.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.member.entity.MemberEntity;
import com.chenyi.gulimall.member.to.MemberInfo;

import java.util.Map;

/**
 * 会员
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:10:10
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取简单用户对象
     * @param userName
     * @return
     */
    MemberInfo getInfoByUserName(String userName);
}

