package com.chenyi.mall.order.mapper;

import com.chenyi.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
	
}
