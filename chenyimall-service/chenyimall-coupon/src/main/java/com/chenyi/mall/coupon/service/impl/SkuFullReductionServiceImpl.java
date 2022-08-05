package com.chenyi.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.coupon.entity.MemberPriceEntity;
import com.chenyi.mall.coupon.entity.SkuFullReductionEntity;
import com.chenyi.mall.coupon.entity.SkuLadderEntity;
import com.chenyi.mall.coupon.mapper.SkuFullReductionMapper;
import com.chenyi.mall.coupon.service.MemberPriceService;
import com.chenyi.mall.coupon.service.SkuFullReductionService;
import com.chenyi.mall.coupon.service.SkuLadderService;
import com.chenyi.mall.api.product.to.MemberPriceDTO;
import com.chenyi.mall.api.product.to.SkuReductionTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReductionEntity> implements SkuFullReductionService {

    @Resource
    private SkuLadderService skuLadderService;

    @Resource
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSkuInfo(SkuReductionTO skuReductionTO) {
        // 保存阶梯价格
        if (skuReductionTO.getFullCount() > 0) {
            SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
            BeanUtils.copyProperties(skuReductionTO, skuLadderEntity);
            skuLadderEntity.setPrice(skuReductionTO.getReducePrice());
            skuLadderEntity.setAddOther(skuReductionTO.getCountStatus());
            skuLadderService.save(skuLadderEntity);
        }

        // 保存满减信息
        if (skuReductionTO.getFullPrice().compareTo(new BigDecimal("0")) > 0) {
            SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
            BeanUtils.copyProperties(skuReductionTO, skuFullReductionEntity);
            this.save(skuFullReductionEntity);
        }

        // 保存会员价格
        List<MemberPriceDTO> memberPrice = skuReductionTO.getMemberPrice();
        List<MemberPriceEntity> memberPriceEntities = memberPrice.stream().map(member -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(skuReductionTO.getSkuId());
            memberPriceEntity.setMemberLevelName(member.getName());
            memberPriceEntity.setMemberLevelId(member.getId());
            memberPriceEntity.setMemberPrice(member.getPrice());
            return memberPriceEntity;
        }).filter(memberPriceEntity ->
                        memberPriceEntity.getMemberPrice().compareTo(new BigDecimal("0")) > 0)
                .collect(Collectors.toList());
        memberPriceService.saveBatch(memberPriceEntities);
    }

}
