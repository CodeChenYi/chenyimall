package com.chenyi.mall.coupon.controller;

import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.coupon.entity.SeckillSkuNoticeEntity;
import com.chenyi.mall.coupon.service.SeckillSkuNoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;



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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SeckillSkuNoticeEntity seckillSkuNotice = seckillSkuNoticeService.getById(id);

        return R.ok().put("seckillSkuNotice", seckillSkuNotice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.save(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody SeckillSkuNoticeEntity seckillSkuNotice){
		seckillSkuNoticeService.updateById(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		seckillSkuNoticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
