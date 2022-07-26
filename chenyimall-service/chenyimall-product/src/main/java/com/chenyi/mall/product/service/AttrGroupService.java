package com.chenyi.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.product.entity.AttrGroupEntity;
import com.chenyi.mall.product.vo.AttrGroupWithAttrsVO;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);


    PageUtils queryPageById(Map<String, Object> map, String catelogId);

    /**
     * 获取属性分组信息和商品信息
     * @param catId
     * @return
     */
    List<AttrGroupWithAttrsVO> getAttrGroupWithAttrByCategoryId(String catId);

    /**
     * 更具categoryId获取分组信息
     * @param catelogId
     * @return
     */
    List<AttrGroupEntity> listCategoryById(String catelogId);
}

