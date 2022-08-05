package com.chenyi.mall.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.member.entity.MemberEntity;
import com.chenyi.mall.member.mapper.MemberMapper;
import com.chenyi.mall.member.service.MemberService;
import com.chenyi.mall.api.member.to.Member;
import com.chenyi.mall.api.member.to.MemberInfo;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MemberInfo getInfoByUserName(String userName) {
        LambdaQueryWrapper<MemberEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MemberEntity::getUsername, userName);
        MemberEntity memberEntity = baseMapper.selectOne(wrapper);
        Member member = new Member();
        BeanUtils.copyProperties(memberEntity, member);
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMember(member);
        return memberInfo;
    }

}