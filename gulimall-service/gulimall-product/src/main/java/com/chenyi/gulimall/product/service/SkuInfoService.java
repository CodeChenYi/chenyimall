package com.chenyi.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.product.dto.SkuDTO;
import com.chenyi.gulimall.product.entity.SkuInfoEntity;
import com.chenyi.gulimall.product.to.SkuInfoTO;
import com.chenyi.gulimall.product.vo.SkuItemVO;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存sku信息
     * @param spuInfoId
     * @param brandId
     * @param catalogId
     * @param skus
     */
    void saveSkuInfo(String spuInfoId, String brandId, String catalogId, List<SkuDTO> skus);

    /**
     * 查询商品详细信息
     * @param skuId
     * @return
     */
    SkuItemVO getItem(String skuId);

    /**
     * 根据skuId查询价格
     *
     * @param skuId
     * @return
     */
    List<SkuInfoTO> getSkuPriceById(List<String> skuId);
}

