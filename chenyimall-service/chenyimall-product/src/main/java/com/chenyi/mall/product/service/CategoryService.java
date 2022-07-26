package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.CategoryEntity;
import com.chenyi.mall.product.vo.CategoryEntityTwoVO;
import com.chenyi.mall.product.vo.CategoryEntityVO;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询商品三级分类树形结构
     * @return
     */
    List<CategoryEntityVO> listWithTree();

    /**
     * 查询catelogId路径
     * @param catelogId
     * @return
     */
    List<String> getCatelogIdPath(String catelogId);

    /**
     * 查询一级分类
     * @return
     */
    List<CategoryEntity> getCategoryLevelOne();

    /**
     * 查询首页分页数据
     * @return
     */
    Map<String, List<CategoryEntityTwoVO>> categoryLevelJson();

    /**
     * 按照名称查询tree
     * @param treeName
     * @return
     */
    List<CategoryEntityVO> listTreeByName(String treeName);

    /**
     * 修改商品三级分类以及中间表信息
     * @param category
     */
    void updateDetail(CategoryEntity category);
}

