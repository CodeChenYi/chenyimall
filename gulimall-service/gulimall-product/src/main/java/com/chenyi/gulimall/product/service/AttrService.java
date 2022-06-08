package com.chenyi.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.product.dto.AttrDTO;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void saveDetail(AttrDTO attrDTO);

    AttrVO getAttrInfo(String attrId);

    /**
     * 修改商品属性
     * @param attrDTO
     */
    void updateDetail(AttrDTO attrDTO);

    /**
     * 根据三级分类id查询商品属性
     *
     * @param params
     * @param catId
     * @param attrType
     * @return
     */
    PageUtils getAttrByCatId(Map<String, Object> params, String catId, Integer attrType);

    /**
     * 删除商品属性详细信息
     * @param asList
     */
    void removeDetail(List<String> asList);

    /**
     * 根据attrGroupId查询分组信息
     * @param attrGroupId
     * @return
     */
    List<AttrEntity> getRelationByAttrGroupId(String attrGroupId);

    /**
     * 获取销售属性信息
     * @param params
     * @return
     */
    PageUtils getSaleInfo(Map<String, Object> params);


    /**
     * 获取销售属性信息
     * @param catId
     * @return
     */
    List<AttrEntity> getSaleList(String catId);
}

