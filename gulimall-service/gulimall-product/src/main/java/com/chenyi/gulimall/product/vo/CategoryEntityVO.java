package com.chenyi.gulimall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author by chenyi
 * @className CategoryEntityVO
 * @date 2021/12/6 19:38
 */

@Data
@EqualsAndHashCode
public class CategoryEntityVO {

    /**
     * 分类id
     */
    @TableId
    private String catId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private String parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;
    /**
     * 子分类数据
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryEntityVO> categoryEntityChildrenList;

}
