package com.chenyi.mall.cart.feign;

import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className CartFeignService
 * @date 2022/6/30 23:26
 */
@FeignClient("mall-cart")
public interface CartFeignService {

    @GetMapping("/cart/getCheckCartItem")
    R getCheckCartItem();

}
