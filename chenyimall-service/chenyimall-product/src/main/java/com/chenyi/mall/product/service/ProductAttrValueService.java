package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.dto.SpuBaseAttrDTO;
import com.chenyi.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu规格参数信息
     * @param spuInfoId
     * @param baseAttrs
     */
    void saveSpuBaseAttr(String spuInfoId, List<SpuBaseAttrDTO> baseAttrs);
}

