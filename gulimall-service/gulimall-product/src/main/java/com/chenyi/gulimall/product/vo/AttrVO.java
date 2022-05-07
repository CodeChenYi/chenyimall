package com.chenyi.gulimall.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenyi
 * @className AttrAttrgroupRelationVO
 * @date 2022/5/5 0:04
 */
@Data
public class AttrVO  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 属性id
     */
    private String attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 属性图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分类
     */
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

    /**
     * 属性分组id
     */
    private String attrGroupId;

    /**
     * 所属三级分类名称
     */
    private String categoryName;

    /**
     * 所属分组名称
     */
    private String AttrGroupName;

    /**
     * 三级分类路径
     */
    private List<String> categoryPath;
}
