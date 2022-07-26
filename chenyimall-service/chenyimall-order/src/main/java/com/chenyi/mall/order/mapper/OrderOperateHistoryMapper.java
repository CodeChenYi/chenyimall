package com.chenyi.mall.order.mapper;

import com.chenyi.mall.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:19
 */
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistoryEntity> {
	
}
