package com.chenyi.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.coupon.entity.SeckillSkuRelationEntity;
import com.chenyi.mall.coupon.service.SeckillSkuRelationService;
import com.chenyi.mall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.mall.common.utils.R;



/**
 * 秒杀活动商品关联
 *
 * @author chenyi
 * @className  SeckillSkuRelation
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/seckillskurelation")
public class SeckillSkuRelationController {
    @Resource
    private SeckillSkuRelationService seckillSkuRelationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:seckillskurelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:seckillskurelation:info")
    public R info(@PathVariable("id") Long id){
		SeckillSkuRelationEntity seckillSkuRelation = seckillSkuRelationService.getById(id);

        return R.ok().put("seckillSkuRelation", seckillSkuRelation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:seckillskurelation:save")
    public R save(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.save(seckillSkuRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:seckillskurelation:update")
    public R update(@RequestBody SeckillSkuRelationEntity seckillSkuRelation){
		seckillSkuRelationService.updateById(seckillSkuRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:seckillskurelation:delete")
    public R delete(@RequestBody Long[] ids){
		seckillSkuRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
