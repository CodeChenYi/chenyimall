package com.chenyi.mall.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;

import com.chenyi.mall.member.mapper.MemberCollectSpuMapper;
import com.chenyi.mall.member.entity.MemberCollectSpuEntity;
import com.chenyi.mall.member.service.MemberCollectSpuService;


@Service("memberCollectSpuService")
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuMapper, MemberCollectSpuEntity> implements MemberCollectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSpuEntity> page = this.page(
                new Query<MemberCollectSpuEntity>().getPage(params),
                new QueryWrapper<MemberCollectSpuEntity>()
        );

        return new PageUtils(page);
    }

}