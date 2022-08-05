package com.chenyi.mall.ware.service.impl;

import com.chenyi.mall.api.ware.to.WareDetailTO;
import com.chenyi.mall.ware.mapper.WareOrderTaskDetailMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;

import com.chenyi.mall.ware.entity.WareOrderTaskDetailEntity;
import com.chenyi.mall.ware.service.WareOrderTaskDetailService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className WareOrderTaskDetailServiceImpl
 * @author chenyi
 * @date 2022/7/30 14:41
 */
@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailMapper, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareOrderTaskDetailEntity> page = this.page(
                new Query<WareOrderTaskDetailEntity>().getPage(params),
                new QueryWrapper<WareOrderTaskDetailEntity>()
        );

        return new PageUtils(page);
    }

}