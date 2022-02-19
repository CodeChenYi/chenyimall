package com.chenyi.gulimall.search.com.chenyi.guilmall.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchParamsDTO {

    /**
     * 三级分类id
     */
    private String categoryThreeId;

    /**
     * 全文匹配关键字
     */
    private String keyword;

    /**
     * 排序条件 sort=saleCount
     */
    private String sort;

    /**
     * 是否显示有货 hasStock=0/1
     */
    private Integer hasStock;

    /**
     * 价格区间查询 1_500/_500/500_
     */
    private String skuPrice;

    /**
     * 品牌id brandId=1&brandId=2
     */
    private List<String> brandId;

    /**
     * 属性筛选 2_5寸:6寸
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum;

}
