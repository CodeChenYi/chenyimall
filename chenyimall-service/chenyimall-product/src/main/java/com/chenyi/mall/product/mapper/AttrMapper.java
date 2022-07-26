package com.chenyi.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chenyi.mall.product.entity.AttrEntity;
import com.chenyi.mall.product.vo.AttrVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Mapper
public interface AttrMapper extends BaseMapper<AttrEntity> {

    List<AttrVO> queryPage(IPage<AttrVO> page, @Param("key") String key);
}
