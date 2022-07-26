package com.chenyi.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.coupon.entity.MemberPriceEntity;
import com.chenyi.mall.coupon.service.MemberPriceService;
import com.chenyi.mall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.mall.common.utils.R;



/**
 * 商品会员价格
 *
 * @author chenyi
 * @className  MemberPrice
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/memberprice")
public class MemberPriceController {
    @Resource
    private MemberPriceService memberPriceService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:memberprice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberPriceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:memberprice:info")
    public R info(@PathVariable("id") Long id){
		MemberPriceEntity memberPrice = memberPriceService.getById(id);

        return R.ok().put("memberPrice", memberPrice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:memberprice:save")
    public R save(@RequestBody MemberPriceEntity memberPrice){
		memberPriceService.save(memberPrice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:memberprice:update")
    public R update(@RequestBody MemberPriceEntity memberPrice){
		memberPriceService.updateById(memberPrice);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:memberprice:delete")
    public R delete(@RequestBody Long[] ids){
		memberPriceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
