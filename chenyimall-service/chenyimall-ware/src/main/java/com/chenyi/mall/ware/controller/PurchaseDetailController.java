package com.chenyi.mall.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.ware.entity.PurchaseDetailEntity;
import com.chenyi.mall.ware.service.PurchaseDetailService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;

import javax.annotation.Resource;


/**
 *
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:13:30
 */
@RestController
@RequestMapping("/purchasedetail")
public class PurchaseDetailController {
    @Resource
    private PurchaseDetailService purchaseDetailService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("ware:purchasedetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("ware:purchasedetail:info")
    public R info(@PathVariable("id") Long id){
		PurchaseDetailEntity purchaseDetail = purchaseDetailService.getById(id);

        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("ware:purchasedetail:save")
    public R save(@RequestBody PurchaseDetailEntity purchaseDetail){
		purchaseDetailService.save(purchaseDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("ware:purchasedetail:update")
    public R update(@RequestBody PurchaseDetailEntity purchaseDetail){
		purchaseDetailService.updateById(purchaseDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("ware:purchasedetail:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
