package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.entity.CategoryBrandRelationEntity;
import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.mapper.CategoryBrandRelationMapper;
import com.chenyi.gulimall.product.service.CategoryBrandRelationService;
import com.chenyi.gulimall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Resource
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> infoByBrandId(String brandId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        String catelogId = categoryBrandRelation.getCatelogId();
        CategoryEntity categoryEntity = categoryService.getById(catelogId);
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        baseMapper.insert(categoryBrandRelation);
    }

    @Override
    public void updateBrand(String brandId, String name) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("brand_id", brandId);
        CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
        relation.setBrandName(name);
        baseMapper.update(relation, wrapper);
    }

    @Override
    public void updateCategory(String catId, String name) {
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelogId", catId);

        CategoryBrandRelationEntity relation = new CategoryBrandRelationEntity();
        relation.setCatelogName(name);
        baseMapper.update(relation, wrapper);
    }
}