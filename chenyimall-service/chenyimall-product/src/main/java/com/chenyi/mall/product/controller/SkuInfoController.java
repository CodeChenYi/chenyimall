package com.chenyi.mall.product.controller;

import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.product.entity.SkuInfoEntity;
import com.chenyi.mall.product.service.SkuInfoService;
import com.chenyi.mall.product.to.SkuInfoTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * sku信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Slf4j
@RestController
@RequestMapping("/skuinfo")
public class SkuInfoController {
    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/getSkuPriceById")
    public R getSkuPriceById(@RequestParam List<String> skuId) {
        List<SkuInfoTO> skuInfoEntities = skuInfoService.getSkuPriceById(skuId);
        log.debug("skuIds: {}", skuInfoEntities);
        return R.ok().put("skuPriceList", skuInfoEntities);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("product:skuinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/listSku")
    public R listSku() {
        List<SkuInfoEntity> list = skuInfoService.list();
        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    public R info(@PathVariable("skuId") String skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);
        SkuInfoTO skuInfoTO = new SkuInfoTO();
        BeanUtils.copyProperties(skuInfo, skuInfoTO);
        return R.ok().put("skuInfo", skuInfoTO);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("product:skuinfo:save")
    public R save(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.save(skuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("product:skuinfo:update")
    public R update(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.updateById(skuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:skuinfo:delete")
    public R delete(@RequestBody Long[] skuIds) {
        skuInfoService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

}
