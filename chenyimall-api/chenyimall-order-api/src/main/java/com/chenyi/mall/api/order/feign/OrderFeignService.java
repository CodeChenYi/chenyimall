package com.chenyi.mall.api.order.feign;

import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chenyi
 * @className OrderFeignService
 * @date 2022/7/31 17:59
 */
@FeignClient("chenyimall-order")
public interface OrderFeignService {

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
    @GetMapping("/order/info/{id}")
    public R info(@PathVariable("id") Long id);

}
