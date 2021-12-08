package com.chenyi.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.coupon.entity.SeckillSkuNoticeEntity;
import com.chenyi.gulimall.coupon.service.SeckillSkuNoticeService;
import com.chenyi.gulimall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.gulimall.common.utils.R;



/**
 * 秒杀商品通知订阅
 *
 * @author chenyi
 * @className  SeckillSkuNotice
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/seckillskunotice")
public class SeckillSkuNoticeController {
    @Resource
    private SeckillSkuNoticeService seckillSkuNoticeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:seckillskunotice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:seckillskunotice:info")
    public R info(@PathVariable("id") Long id){
		SeckillSkuNoticeEntity seckillSkuNotice = seckillSkuNoticeService.getById(id);

        return R.ok().put("seckillSkuNotice", seckillSkuNotice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:seckillskunotice:save")
    public R save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.save(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:seckillskunotice:update")
    public R update(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.updateById(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:seckillskunotice:delete")
    public R delete(@RequestBody Long[] ids){
		seckillSkuNoticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
