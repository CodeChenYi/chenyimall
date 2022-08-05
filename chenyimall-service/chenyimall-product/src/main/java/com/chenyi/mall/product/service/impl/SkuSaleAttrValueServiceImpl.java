package com.chenyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.product.service.SkuSaleAttrValueService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.product.entity.SkuSaleAttrValueEntity;
import com.chenyi.mall.product.mapper.SkuSaleAttrValueMapper;
import com.chenyi.mall.product.vo.SkuItemVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Resource
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuItemVO.SkuSaleAttrVO> getSaleAttrValue(String spuId) {
        return skuSaleAttrValueMapper.getSaleAttrValue(spuId);
    }

    @Override
    public List<String> getSaleAttrBySkuId(String skuId) {
        return baseMapper.getSaleAttrBySkuId(skuId);
    }

}