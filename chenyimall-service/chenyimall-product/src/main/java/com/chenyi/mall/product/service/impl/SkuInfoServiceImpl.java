package com.chenyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.api.coupon.feign.CouponFeignService;
import com.chenyi.mall.product.dto.SkuDTO;
import com.chenyi.mall.product.dto.SpuImagesDTO;
import com.chenyi.mall.product.entity.*;
import com.chenyi.mall.product.mapper.SkuInfoMapper;
import com.chenyi.mall.api.product.to.SkuInfoTO;
import com.chenyi.mall.api.product.to.SkuReductionTO;
import com.chenyi.mall.product.vo.SkuItemVO;
import com.chenyi.mall.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @className SkuInfoServiceImpl
 * @author chenyi
 * @date 2022/9/5 11:29
 */
@Slf4j
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoEntity> implements SkuInfoService {

    @Resource
    private SkuImagesService skuImagesService;

    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Resource
    private CouponFeignService couponFeignService;

    @Resource
    private SpuInfoService spuInfoService;

    @Resource
    private SpuInfoDescService spuInfoDescService;

    @Resource
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSkuInfo(String spuInfoId, String brandId, String catalogId, List<SkuDTO> skus) {
        skus.forEach(sku -> {
            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            // 判断是否是默认图片
            List<SpuImagesDTO> images = sku.getImages();
            String defaultImg = "";
            for (SpuImagesDTO image : images) {
                if (image.getDefaultImg() == 1) {
                    defaultImg = image.getImgUrl();
                }
            }
            BeanUtils.copyProperties(sku, skuInfoEntity);
            skuInfoEntity.setBrandId(brandId);
            skuInfoEntity.setCatalogId(catalogId);
            skuInfoEntity.setSpuId(spuInfoId);
            skuInfoEntity.setSaleCount(0L);
            skuInfoEntity.setSkuDefaultImg(defaultImg);
            // 保存当前sku信息
            baseMapper.insert(skuInfoEntity);
            String skuId = skuInfoEntity.getSkuId();
            // 保存当前sku图片信息
            List<SkuImagesEntity> imagesEntities = images.stream().map(image -> {
                SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                skuImagesEntity.setSkuId(skuId);
                skuImagesEntity.setImgUrl(image.getImgUrl());
                skuImagesEntity.setDefaultImg(image.getDefaultImg());
                return skuImagesEntity;
            }).filter(img -> !StringUtils.isEmpty(img.getImgUrl())).collect(Collectors.toList());
            skuImagesService.saveBatch(imagesEntities);
            // 保存sku销售属性信息
            List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = sku.getAttr().stream()
                    .map(saleAttr -> {
                        SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                        BeanUtils.copyProperties(saleAttr, skuSaleAttrValueEntity);
                        skuSaleAttrValueEntity.setSkuId(skuId);
                        return skuSaleAttrValueEntity;
                    }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

            // 保存积分信息
            SkuReductionTO skuReductionTO = new SkuReductionTO();
            BeanUtils.copyProperties(sku, skuReductionTO);
            skuReductionTO.setSkuId(skuId);
            if (skuReductionTO.getFullCount() > 0 ||
                    skuReductionTO.getFullPrice().compareTo(new BigDecimal("0")) > 0) {
                R r = couponFeignService.saveSkuReduction(skuReductionTO);
                if (!ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                    log.error(ResultEnum.REMOTE_CALL_FAIL.getMsg());
                }
            }
        });

    }

    @Override
    public SkuItemVO getItem(String skuId) {
        SkuItemVO skuItemVO = new SkuItemVO();
        // 使用异步的方式来进行查询封装
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            // 查询sku详细信息
            SkuInfoEntity skuInfoEntity = baseMapper.selectById(skuId);
            skuItemVO.setSkuInfo(skuInfoEntity);
            return skuInfoEntity;
        }, executor);

        // 查询sku图片信息
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            QueryWrapper<SkuImagesEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("sku_id", skuId);
            List<SkuImagesEntity> images = skuImagesService.list(wrapper);
            skuItemVO.setSkuImagesList(images);
        }, executor);

        // 查询spu详细信息
        CompletableFuture<Void> spuInfoFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                SpuInfoEntity spuInfo = spuInfoService.getById(skuInfo.getSpuId());
                skuItemVO.setSpuInfo(spuInfo);
            }
        }, executor);

        // 查询sku销售属性信息
        CompletableFuture<Void> skuSaleFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                List<SkuItemVO.SkuSaleAttrVO> skuSaleAttrVOList = skuSaleAttrValueService.getSaleAttrValue(skuInfo.getSpuId());
                log.info(skuSaleAttrVOList.toString());
                skuItemVO.setSaleAttrVOList(skuSaleAttrVOList);
            }
        }, executor);

        // 查询spu详细信息
        CompletableFuture<Void> spuDescFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
                skuItemVO.setSpuInfoDesc(spuInfoDesc);
            }
        }, executor);

        // 查询商品规格参数信息
        CompletableFuture<Void> attrGroupFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            if (skuInfo != null) {
                List<SkuItemVO.SpuAttrGroupVO> spuAttrGroupVOList = spuInfoService.getSpuBaseAttrInfo(skuInfo.getSpuId());
                skuItemVO.setSpuAttrGroupVOList(spuAttrGroupVOList);
            }
        }, executor);

        // 等待所有异步任务都完成后再进行返回
        CompletableFuture.allOf(
                spuInfoFuture,
                skuSaleFuture,
                spuDescFuture,
                attrGroupFuture,
                imageFuture).join();

        return skuItemVO;
    }

    @Override
    public List<SkuInfoTO> getSkuPriceById(List<String> skuId) {
       return baseMapper.getSkuPriceById(skuId);
    }

}