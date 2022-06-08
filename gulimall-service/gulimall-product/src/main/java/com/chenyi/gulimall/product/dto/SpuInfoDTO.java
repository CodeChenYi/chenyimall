package com.chenyi.gulimall.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyi
 * @className SpuInfoDTO
 * @date 2022/5/9 23:58
 */
@Data
public class SpuInfoDTO {
        /**
         * spu名称
         */
        private String spuName;
        /**
         * spu描述
         */
        private String spuDescription;
        /**
         * spu三级分类id
         */
        private String catalogId;
        /**
         * spu品牌id
         */
        private String brandId;
        /**
         * spu重量
         */
        private BigDecimal weight;
        /**
         * spu发布状态
         */
        private int publishStatus;
        /**
         * spu商品介绍图片
         */
        private List<String> decript;
        /**
         * spu图片信息
         */
        private List<String> images;
        /**
         * 购买所获得的积分
         */
        private BoundsDTO bounds;
        /**
         * 基本属性信息
         */
        private List<SpuBaseAttrDTO> baseAttrs;
        /**
         * sku信息
         */
        private List<SkuDTO> skus;
}
