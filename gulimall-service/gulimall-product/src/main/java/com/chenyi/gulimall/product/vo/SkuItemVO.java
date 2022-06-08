package com.chenyi.gulimall.product.vo;

import com.chenyi.gulimall.product.entity.SkuImagesEntity;
import com.chenyi.gulimall.product.entity.SkuInfoEntity;
import com.chenyi.gulimall.product.entity.SpuInfoDescEntity;
import com.chenyi.gulimall.product.entity.SpuInfoEntity;
import lombok.Data;

import java.util.List;

/**
 * @author chenyi
 * @className SkuItemVO
 * @date 2022/5/14 15:23
 */
@Data
public class SkuItemVO {

    private SpuInfoEntity spuInfo;

    /**
     * sku信息
     */
    private SkuInfoEntity skuInfo;

    /**
     * sku图片信息
     */
    private List<SkuImagesEntity> skuImagesList;

    /**
     * sku销售属性信息
     */
    List<SkuSaleAttrVO> saleAttrVOList;
    /**
     * spu介绍
     */
    private SpuInfoDescEntity spuInfoDesc;

    /**
     * spu规格参数
     */
    private List<SpuAttrGroupVO> spuAttrGroupVOList;

    @Data
    public static class SkuSaleAttrVO {
        private String attrId;
        private String attrName;
        private String attrValueList;
    }

    @Data
    public static class SpuAttrGroupVO {
        private String attrGroupId;
        private String attrGroupName;
        private List<SpuBaseAttrVO> spuBaseAttrVOList;
    }

    @Data
    public static class SpuBaseAttrVO {
        private String attrName;
        private String attrValue;
    }


}
