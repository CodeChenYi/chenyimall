package com.chenyi.mall.product.controller;

import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.product.entity.BrandEntity;
import com.chenyi.mall.product.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 品牌
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }

    @PutMapping("/update/ShowStatus")
    public R updateShowStatus(@RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") String brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody BrandEntity brand){
		brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody String[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
