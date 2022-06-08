package com.chenyi.gulimall.product.vo;

import com.chenyi.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author chenyi
 * @className AttrGroupWithAttrsVO
 * @date 2022/5/9 13:24
 */
@Data
public class AttrGroupWithAttrsVO {


    /**
     * 分组id
     */
    private String attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private String catelogId;

    /**
     * AttrList
     */
    private List<AttrEntity> attrEntityList;
}
