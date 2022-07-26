package com.chenyi.mall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.order.entity.OrderSettingEntity;
import com.chenyi.mall.order.service.OrderSettingService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;

import javax.annotation.Resource;


/**
 * 订单配置信息
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
@RestController
@RequestMapping("order/ordersetting")
public class OrderSettingController {
    @Resource
    private OrderSettingService orderSettingService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("order:ordersetting:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderSettingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("order:ordersetting:info")
    public R info(@PathVariable("id") Long id){
		OrderSettingEntity orderSetting = orderSettingService.getById(id);

        return R.ok().put("orderSetting", orderSetting);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("order:ordersetting:save")
    public R save(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.save(orderSetting);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("order:ordersetting:update")
    public R update(@RequestBody OrderSettingEntity orderSetting){
		orderSettingService.updateById(orderSetting);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("order:ordersetting:delete")
    public R delete(@RequestBody Long[] ids){
		orderSettingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
