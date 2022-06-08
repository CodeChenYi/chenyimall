package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.exception.GuliMallException;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.coupon.feign.CouponFeignService;
import com.chenyi.gulimall.product.dto.BoundsDTO;
import com.chenyi.gulimall.product.dto.SkuDTO;
import com.chenyi.gulimall.product.dto.SpuInfoDTO;
import com.chenyi.gulimall.product.entity.SpuInfoEntity;
import com.chenyi.gulimall.product.mapper.SpuInfoMapper;
import com.chenyi.gulimall.product.service.*;
import com.chenyi.gulimall.product.to.SpuBoundTO;
import com.chenyi.gulimall.product.vo.SkuItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfoEntity> implements SpuInfoService {

    @Resource
    private SpuImagesService spuImagesService;

    @Resource
    private SpuInfoDescService spuInfoDescService;

    @Resource
    private ProductAttrValueService productAttrValueService;

    @Resource
    private SkuInfoService skuInfoService;

    @Resource
    private CouponFeignService couponFeignService;

    @Resource
    private SpuInfoMapper spuInfoMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSpuInfoDTO(SpuInfoDTO spuInfoDTO) {
        // 保存Spu信息
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfoDTO, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.SaveSpuInfo(spuInfoEntity);
        // 保存Spu图片信息
        String SpuInfoId = spuInfoEntity.getId();
        List<String> images = spuInfoDTO.getImages();
        spuImagesService.saveSpuImages(spuInfoEntity.getSpuName(), SpuInfoId, images);
        // 保存Spu描述信息
        spuInfoDescService.saveSpuDesc(SpuInfoId, spuInfoDTO.getDecript());
        // 保存spu规格参数信息
        productAttrValueService.saveSpuBaseAttr(SpuInfoId, spuInfoDTO.getBaseAttrs());
        // 保存Sku信息
        List<SkuDTO> skus = spuInfoDTO.getSkus();
        skuInfoService.saveSkuInfo(SpuInfoId, spuInfoDTO.getBrandId(), spuInfoDTO.getCatalogId(), skus);
        // 保存会员信息
        BoundsDTO bounds = spuInfoDTO.getBounds();
        SpuBoundTO spuBoundTO = new SpuBoundTO();
        BeanUtils.copyProperties(bounds, spuBoundTO);
        spuBoundTO.setSpuId(SpuInfoId);
        R r = couponFeignService.saveSpuBound(spuBoundTO);
        if (!ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
            log.error(ResultEnum.REMOTE_CALL_FAIL.getMsg());
            throw new GuliMallException(ResultEnum.REMOTE_CALL_FAIL.getCode(),
                    ResultEnum.REMOTE_CALL_FAIL.getMsg());
        }
    }

    @Override
    public void SaveSpuInfo(SpuInfoEntity spuInfoEntity) {
        baseMapper.insert(spuInfoEntity);
    }

    @Override
    public List<SkuItemVO.SpuAttrGroupVO> getSpuBaseAttrInfo(String spuId) {
        return spuInfoMapper.getSpuBaseAttrInfo(spuId);
    }

}