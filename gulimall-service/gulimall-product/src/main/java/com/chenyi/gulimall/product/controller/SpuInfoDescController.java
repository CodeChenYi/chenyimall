package com.chenyi.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.product.entity.SpuInfoDescEntity;
import com.chenyi.gulimall.product.service.SpuInfoDescService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;

import javax.annotation.Resource;


/**
 * spu信息介绍
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
@RestController
@RequestMapping("/spuinfodesc")
public class SpuInfoDescController {
    @Resource
    private SpuInfoDescService spuInfoDescService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("product:spuinfodesc:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoDescService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{spuId}")
    // @RequiresPermissions("product:spuinfodesc:info")
    public R info(@PathVariable("spuId") Long spuId){
		SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(spuId);

        return R.ok().put("spuInfoDesc", spuInfoDesc);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("product:spuinfodesc:save")
    public R save(@RequestBody SpuInfoDescEntity spuInfoDesc){
		spuInfoDescService.save(spuInfoDesc);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("product:spuinfodesc:update")
    public R update(@RequestBody SpuInfoDescEntity spuInfoDesc){
		spuInfoDescService.updateById(spuInfoDesc);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:spuinfodesc:delete")
    public R delete(@RequestBody Long[] spuIds){
		spuInfoDescService.removeByIds(Arrays.asList(spuIds));

        return R.ok();
    }

}
