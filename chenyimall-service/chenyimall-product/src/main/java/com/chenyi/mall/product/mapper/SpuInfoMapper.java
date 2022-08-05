package com.chenyi.mall.product.mapper;

import com.chenyi.mall.product.entity.SpuInfoEntity;
import com.chenyi.mall.product.vo.SkuItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu信息
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfoEntity> {

    List<SkuItemVO.SpuAttrGroupVO> getSpuBaseAttrInfo(@Param("spuId") String spuId);
}
