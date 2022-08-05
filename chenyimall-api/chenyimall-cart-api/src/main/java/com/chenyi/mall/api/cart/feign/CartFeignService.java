package com.chenyi.mall.api.cart.feign;

import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className CartFeignService
 * @date 2022/6/30 23:26
 */
@FeignClient("chenyimall-cart")
public interface CartFeignService {

    /**
     * 获取购物车选中项
     * @return
     */
    @GetMapping("/cart/getCheckCartItem")
    R getCheckCartItem();

}
