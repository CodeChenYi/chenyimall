package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu图片
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu图片信息
     * @param spuName
     * @param id
     * @param images
     */
    void saveSpuImages(String spuName, String id, List<String> images);
}

