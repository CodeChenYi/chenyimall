package com.chenyi.mall.api.ware.feign;

import com.chenyi.mall.api.order.to.LockOrderItemTO;
import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author chenyi
 * @className WareFeignService
 * @date 2022/7/1 17:07
 */
@FeignClient("chenyimall-ware")
public interface WareFeignService {

    /**
     * 通过skuid获取库存信息
     * @param skuId
     * @return
     */
    @GetMapping("/waresku/infoBySkuId/{skuId}")
    R infoBySkuId(@PathVariable List<String> skuId);

    /**
     * 锁定库存
     * @param orderItems
     * @return
     */
    @PostMapping("/waresku/lockOrderWare")
    R lockOrderWare(@RequestBody List<LockOrderItemTO> orderItems);

}
