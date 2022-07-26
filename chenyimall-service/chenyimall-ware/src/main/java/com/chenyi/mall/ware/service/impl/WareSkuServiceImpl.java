package com.chenyi.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.ware.entity.WareInfoEntity;
import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.chenyi.mall.ware.mapper.WareSkuMapper;
import com.chenyi.mall.ware.service.WareInfoService;
import com.chenyi.mall.ware.service.WareSkuService;
import com.chenyi.mall.ware.to.WareSkuTo;
import com.chenyi.mall.ware.vo.WareSkuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSkuEntity> implements WareSkuService {

    @Resource
    private WareInfoService wareInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );
        List<WareSkuEntity> records = page.getRecords();
        List<WareSkuVO> wareSkuVOList = records.stream().map(wareSku -> {
            WareSkuVO wareSkuVO = new WareSkuVO();
            BeanUtils.copyProperties(wareSku, wareSkuVO);
            String wareId = wareSku.getWareId();
            WareInfoEntity wareInfo = wareInfoService.getById(wareId);
            if (wareInfo != null) {
                wareSkuVO.setWareName(wareInfo.getName());
            }
            return wareSkuVO;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(wareSkuVOList);
        return pageUtils;
    }

    @Override
    public List<WareSkuTo> infoBySkuId(List<String> skuId) {
        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();
        wrapper.in("sku_id", skuId);
        List<WareSkuEntity> wareSkuEntities = baseMapper.selectList(wrapper);
        return wareSkuEntities.stream().map(wareSkuEntity -> {
            WareSkuTo wareSkuTo = new WareSkuTo();
            BeanUtils.copyProperties(wareSkuEntity, wareSkuTo);
            return wareSkuTo;
        }).collect(Collectors.toList());
    }

}