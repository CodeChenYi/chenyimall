package com.chenyi.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.coupon.entity.SpuBoundsEntity;
import com.chenyi.gulimall.coupon.service.SpuBoundsService;
import com.chenyi.gulimall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.gulimall.common.utils.R;



/**
 * 商品spu积分设置
 *
 * @author chenyi
 * @className  SpuBounds
 * @date 2021-12-07 01:27:48
 */
@RestController
@RequestMapping("/spubounds")
public class SpuBoundsController {
    @Resource
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:spubounds:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBoundsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:spubounds:info")
    public R info(@PathVariable("id") Long id){
		SpuBoundsEntity spuBounds = spuBoundsService.getById(id);

        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:spubounds:save")
    public R save(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.save(spuBounds);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:spubounds:update")
    public R update(@RequestBody SpuBoundsEntity spuBounds){
		spuBoundsService.updateById(spuBounds);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:spubounds:delete")
    public R delete(@RequestBody Long[] ids){
		spuBoundsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
