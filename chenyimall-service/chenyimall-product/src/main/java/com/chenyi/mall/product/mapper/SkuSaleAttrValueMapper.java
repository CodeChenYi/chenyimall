package com.chenyi.mall.product.mapper;

import com.chenyi.mall.product.entity.SkuSaleAttrValueEntity;
import com.chenyi.mall.product.vo.SkuItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValueEntity> {

    List<SkuItemVO.SkuSaleAttrVO> getSaleAttrValue(@Param("spuId") String spuId);

    List<String> getSaleAttrBySkuId(@Param("skuId") String skuId);
}
