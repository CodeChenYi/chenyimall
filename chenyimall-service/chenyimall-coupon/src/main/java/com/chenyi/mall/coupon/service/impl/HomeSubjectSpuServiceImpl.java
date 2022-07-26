package com.chenyi.mall.coupon.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;

import com.chenyi.mall.coupon.mapper.HomeSubjectSpuMapper;
import com.chenyi.mall.coupon.entity.HomeSubjectSpuEntity;
import com.chenyi.mall.coupon.service.HomeSubjectSpuService;


@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpuEntity> implements HomeSubjectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpuEntity> page = this.page(
                new Query<HomeSubjectSpuEntity>().getPage(params),
                new QueryWrapper<HomeSubjectSpuEntity>()
        );

        return new PageUtils(page);
    }

}
