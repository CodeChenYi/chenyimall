package com.chenyi.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.coupon.entity.HomeSubjectSpuEntity;
import com.chenyi.gulimall.coupon.service.HomeSubjectSpuService;
import com.chenyi.gulimall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.gulimall.common.utils.R;



/**
 * 专题商品
 *
 * @author chenyi
 * @className  HomeSubjectSpu
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/homesubjectspu")
public class HomeSubjectSpuController {
    @Resource
    private HomeSubjectSpuService homeSubjectSpuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:homesubjectspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeSubjectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:homesubjectspu:info")
    public R info(@PathVariable("id") Long id){
		HomeSubjectSpuEntity homeSubjectSpu = homeSubjectSpuService.getById(id);

        return R.ok().put("homeSubjectSpu", homeSubjectSpu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:homesubjectspu:save")
    public R save(@RequestBody HomeSubjectSpuEntity homeSubjectSpu){
		homeSubjectSpuService.save(homeSubjectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:homesubjectspu:update")
    public R update(@RequestBody HomeSubjectSpuEntity homeSubjectSpu){
		homeSubjectSpuService.updateById(homeSubjectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:homesubjectspu:delete")
    public R delete(@RequestBody Long[] ids){
		homeSubjectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
