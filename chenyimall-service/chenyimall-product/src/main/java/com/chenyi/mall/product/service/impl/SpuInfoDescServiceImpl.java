package com.chenyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.product.entity.SpuInfoDescEntity;
import com.chenyi.mall.product.mapper.SpuInfoDescMapper;
import com.chenyi.mall.product.service.SpuInfoDescService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDescEntity> implements SpuInfoDescService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoDescEntity> page = this.page(
                new Query<SpuInfoDescEntity>().getPage(params),
                new QueryWrapper<SpuInfoDescEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuDesc(String id, List<String> spuDescription) {
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(id);
        spuInfoDescEntity.setDecript(String.join("，", spuDescription));
        baseMapper.insert(spuInfoDescEntity);
    }

}