package com.chenyi.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyi.mall.product.entity.SkuInfoEntity;
import com.chenyi.mall.api.product.to.SkuInfoTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku信息
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfoEntity> {

    List<SkuInfoTO> getSkuPriceById(@Param("skuIds") List<String> skuIds);
}
