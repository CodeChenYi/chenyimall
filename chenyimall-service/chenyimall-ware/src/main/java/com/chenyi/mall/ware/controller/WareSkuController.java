package com.chenyi.mall.ware.controller;

import com.chenyi.mall.api.order.to.LockOrderItemTO;
import com.chenyi.mall.ware.entity.WareSkuEntity;
import com.chenyi.mall.ware.service.WareSkuService;
import com.chenyi.mall.api.ware.to.WareSkuTo;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.api.order.to.OrderItemTO;
import com.chenyi.mall.ware.dto.WareSkuDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品库存
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:13:30
 */
@RestController
@RequestMapping("/waresku")
public class WareSkuController {
    @Resource
    private WareSkuService wareSkuService;


    @PostMapping("/lockOrderWare")
    public R lockOrderWare(@RequestBody List<LockOrderItemTO> orderItems) {
        boolean flag = wareSkuService.lockOrderWare(orderItems);
        if (flag) {
            return R.ok();
        }
        return R.error(ResultEnum.PRODUCT_NO_STOCK.getCode(), ResultEnum.PRODUCT_NO_STOCK.getMsg());
    }

    @GetMapping("/infoBySkuId/{skuId}")
    public R infoBySkuId(@PathVariable List<String> skuId) {
        List<WareSkuTo> wareSkuTo = wareSkuService.infoBySkuId(skuId);
        return R.ok().put("wareSkuList", wareSkuTo);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("ware:waresku:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("ware:waresku:info")
    public R info(@PathVariable("id") String id) {
        WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("ware:waresku:save")
    public R save(@RequestBody WareSkuDTO wareSkuDTO) {
        WareSkuEntity wareSkuEntity = new WareSkuEntity();
        BeanUtils.copyProperties(wareSkuDTO, wareSkuEntity);
        wareSkuService.save(wareSkuEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("ware:waresku:update")
    public R update(@RequestBody WareSkuEntity wareSku) {
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("ware:waresku:delete")
    public R delete(@RequestBody String[] ids) {
        wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
