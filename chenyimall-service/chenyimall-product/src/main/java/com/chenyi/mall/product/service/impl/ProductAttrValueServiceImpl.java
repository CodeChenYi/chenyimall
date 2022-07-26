package com.chenyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.product.dto.SpuBaseAttrDTO;
import com.chenyi.mall.product.entity.AttrEntity;
import com.chenyi.mall.product.entity.ProductAttrValueEntity;
import com.chenyi.mall.product.mapper.ProductAttrValueMapper;
import com.chenyi.mall.product.service.AttrService;
import com.chenyi.mall.product.service.ProductAttrValueService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValueEntity> implements ProductAttrValueService {

    @Resource
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuBaseAttr(String spuInfoId, List<SpuBaseAttrDTO> baseAttrs) {
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(baseAttr -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            BeanUtils.copyProperties(baseAttr, productAttrValueEntity);
            AttrEntity attrEntity = attrService.getById(baseAttr.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrValue(baseAttr.getAttrValues());
            productAttrValueEntity.setQuickShow(baseAttr.getShowDesc());
            productAttrValueEntity.setSpuId(spuInfoId);
            return productAttrValueEntity;
        }).collect(Collectors.toList());

        this.saveBatch(productAttrValueEntities);
    }

}