package com.chenyi.gulimall.product.mapper;

import com.chenyi.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyi.gulimall.product.vo.SkuItemVO;
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
}
