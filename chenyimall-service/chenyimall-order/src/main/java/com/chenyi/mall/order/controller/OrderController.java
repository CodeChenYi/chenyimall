package com.chenyi.mall.order.controller;

import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.order.dto.OrderDTO;
import com.chenyi.mall.order.entity.OrderEntity;
import com.chenyi.mall.order.service.OrderService;
import com.chenyi.mall.order.vo.OrderBackInfoVO;
import com.chenyi.mall.order.vo.OrderConfirmVO;
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

    @PutMapping("/closeOrder/{id}")
    public R closeOrder(@PathVariable Long id) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderService.closeOrder(orderEntity);
        return R.ok();
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody OrderDTO order) {
        OrderBackInfoVO orderBackInfoVO = orderService.saveOrder(order);
        return R.ok().put("orderBackInfo", orderBackInfoVO);
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
