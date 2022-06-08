package com.chenyi.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.product.dto.SpuInfoDTO;
import com.chenyi.gulimall.product.entity.SpuInfoEntity;
import com.chenyi.gulimall.product.vo.SkuItemVO;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存商品详细信息
     * @param spuInfoDTO
     */
    void saveSpuInfoDTO(SpuInfoDTO spuInfoDTO);

    /**
     * 保存Spu信息
     * @param spuInfoEntity
     */
    void SaveSpuInfo(SpuInfoEntity spuInfoEntity);

    /**
     * 查询Spu规格参数信息
     * @param spuId
     * @return
     */
    List<SkuItemVO.SpuAttrGroupVO> getSpuBaseAttrInfo(String spuId);
}

