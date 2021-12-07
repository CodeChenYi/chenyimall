package com.chenyi.gulimall.order.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.order.entity.PaymentInfoEntity;
import com.chenyi.gulimall.order.service.PaymentInfoService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;

import javax.annotation.Resource;


/**
 * 支付信息表
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {
    @Resource
    private PaymentInfoService paymentInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("order:paymentinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("order:paymentinfo:info")
    public R info(@PathVariable("id") Long id){
		PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);

        return R.ok().put("paymentInfo", paymentInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("order:paymentinfo:save")
    public R save(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.save(paymentInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("order:paymentinfo:update")
    public R update(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.updateById(paymentInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("order:paymentinfo:delete")
    public R delete(@RequestBody Long[] ids){
		paymentInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
