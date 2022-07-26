package com.chenyi.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.coupon.entity.SeckillPromotionEntity;
import com.chenyi.mall.coupon.service.SeckillPromotionService;
import com.chenyi.mall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.mall.common.utils.R;



/**
 * 秒杀活动
 *
 * @author chenyi
 * @className  SeckillPromotion
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/seckillpromotion")
public class SeckillPromotionController {
    @Resource
    private SeckillPromotionService seckillPromotionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:seckillpromotion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillPromotionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:seckillpromotion:info")
    public R info(@PathVariable("id") Long id){
		SeckillPromotionEntity seckillPromotion = seckillPromotionService.getById(id);

        return R.ok().put("seckillPromotion", seckillPromotion);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:seckillpromotion:save")
    public R save(@RequestBody SeckillPromotionEntity seckillPromotion){
		seckillPromotionService.save(seckillPromotion);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:seckillpromotion:update")
    public R update(@RequestBody SeckillPromotionEntity seckillPromotion){
		seckillPromotionService.updateById(seckillPromotion);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:seckillpromotion:delete")
    public R delete(@RequestBody Long[] ids){
		seckillPromotionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
