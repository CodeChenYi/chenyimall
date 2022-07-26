package com.chenyi.mall.search.com.chenyi.guilmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchResultVO {

    /**
     * 查询到的所有商品信息
     */
    private List products;

    /**
     * 当前查询到的结果所涉及到的品牌
     */
    private List<BrandVO> brandVOList;

    /**
     * 当前查询到的结果涉及到的分类
     */
    private List<CategoryVO> categoryVOList;

    /**
     * 当前查询结果涉及到的属性
     */
    private List<AttrVO> attrVOList;

    /**
     * 当前页码
     */
    private Long pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总分页数
     */
    private Long totalPages;

    @Data
    public static class BrandVO {
        private String brandId;
        private String brandName;
        private String brandImage;
    }

    @Data
    public static class CategoryVO{
        private String categoryId;
        private String categoryName;
    }

    @Data
    public static class AttrVO{
        private String attrId;
        private String attrName;
        private String attrValue;
    }
}
