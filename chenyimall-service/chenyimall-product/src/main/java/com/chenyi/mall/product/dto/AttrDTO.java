package com.chenyi.mall.product.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * @author chenyi
 * @className AttrDTO
 * @date 2022/5/4 22:00
 */
@Data
public class AttrDTO {

    /**
     * 属性id
     */
    private String attrId;
    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空")
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @NotNull(message = "检索类型不能为空")
    @Min(value = 0, message = "检索类型只能为数字")
    @Max(value = 1, message = "检索类型只能为数字")
    private Integer searchType;
    /**
     * 属性图标
     */
    @NotBlank(message = "icon不能为空")
    @URL(message = "icon必须是一个链接地址")
    private String icon;

    /**
     * 是否支持多个值【0否，1是】
     */
    private Integer valueMany;
    /**
     * 可选值列表[用逗号分隔]
     */
    @NotBlank(message = "可选值列表不能为空")
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @NotNull
    @Min(value = 0, message = "检索类型只能为数字")
    @Max(value = 1, message = "检索类型只能为数字")
    private Long enable;
    /**
     * 所属分类
     */
    @NotBlank
    private String catelogId;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @NotNull
    @Min(value = 0, message = "检索类型只能为数字")
    @Max(value = 1, message = "检索类型只能为数字")
    private Integer showDesc;

    /**
     * 商品分组id
     */
    @NotBlank
    private String attrGroupId;
}
