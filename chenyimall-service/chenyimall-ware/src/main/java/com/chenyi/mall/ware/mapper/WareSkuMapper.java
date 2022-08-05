package com.chenyi.mall.ware.mapper;

import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:13:30
 */
@Mapper
public interface WareSkuMapper extends BaseMapper<WareSkuEntity> {

    /**
     * 查询所有仓库是否有库存
     *
     * @param skuId
     * @param skuQuantity
     * @return
     */
    List<Long> selectIsItInStock(@Param("skuId") Long skuId, @Param("skuQuantity") Integer skuQuantity);

    /**
     * 锁定库存
     * @param wareId
     * @param skuId
     * @param num
     * @return
     */
    int lockStock(@Param("wareId") Long wareId, @Param("skuId") Long skuId, @Param("num") Integer num);

    /**
     * 解锁库存
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void unLockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);
}
