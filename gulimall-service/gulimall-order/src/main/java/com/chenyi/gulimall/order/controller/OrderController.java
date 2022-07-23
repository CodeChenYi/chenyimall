package com.chenyi.gulimall.order.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.order.dto.OrderDTO;
import com.chenyi.gulimall.order.entity.OrderEntity;
import com.chenyi.gulimall.order.service.OrderService;
import com.chenyi.gulimall.order.vo.OrderConfirmVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 订单
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:07:20
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;


    /**
     * 获取确认订单页面数据
     * @return
     */
    @GetMapping("/getOrderConfirm")
    public R getOrderConfirm() {
        OrderConfirmVO confirmVO = orderService.getOrderConfirm();
        return R.ok().put("orderConfirm", confirmVO);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("order:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("order:order:info")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("order:order:save")
    public R save(@RequestBody OrderDTO order) {
        orderService.saveOrder(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("order:order:update")
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("order:order:delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
