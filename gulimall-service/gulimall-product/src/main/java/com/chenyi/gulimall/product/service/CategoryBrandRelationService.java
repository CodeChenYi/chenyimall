package com.chenyi.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据品牌id查询分组信息
     * @param brandId
     * @return
     */
    List<CategoryBrandRelationEntity> infoByBrandId(String brandId);

    /**
     * 添加类别品牌关系
     * @param categoryBrandRelation
     */
    void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新中间表中品牌信息
     * @param brandId
     * @param name
     */
    void updateBrand(String brandId, String name);

    /**
     * 更新三级分类信息
     * @param catId
     * @param name
     */
    void updateCategory(String catId, String name);
}

