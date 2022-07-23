package com.chenyi.gulimall.cart.feign;

import com.chenyi.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className CartFeignService
 * @date 2022/6/30 23:26
 */
@FeignClient("gulimall-cart")
public interface CartFeignService {

    @GetMapping("/cart/getCheckCartItem")
    R getCheckCartItem();

}
