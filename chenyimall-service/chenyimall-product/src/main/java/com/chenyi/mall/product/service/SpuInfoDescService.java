package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.SpuInfoDescEntity;

import java.util.List;
import java.util.Map;

/**
 * spu信息介绍
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu描述信息
     * @param id
     * @param spuDescription
     */
    void saveSpuDesc(String id, List<String> spuDescription);
}

