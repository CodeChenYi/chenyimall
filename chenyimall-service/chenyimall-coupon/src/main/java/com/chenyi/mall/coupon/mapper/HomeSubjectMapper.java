package com.chenyi.mall.coupon.mapper;

import com.chenyi.mall.coupon.entity.HomeSubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-12-07 01:27:49
 */
@Mapper
public interface HomeSubjectMapper extends BaseMapper<HomeSubjectEntity> {

}
