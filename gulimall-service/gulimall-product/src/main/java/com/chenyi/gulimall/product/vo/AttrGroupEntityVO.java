package com.chenyi.gulimall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author by chenyi
 * @className AttrGroupEntityVO
 * @date 2022/2/14 0:09
 */
@Data
public class AttrGroupEntityVO {
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
     * catelog完整路径
     */
    private List<String> catelogIdPath;

    /**
     * 三级分类名称
     */
    private String categoryName;
}
