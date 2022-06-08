package com.chenyi.gulimall.coupon.feign;

import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.to.SkuReductionTO;
import com.chenyi.gulimall.product.to.SpuBoundTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chenyi
 * @className CouponFeignService
 * @date 2022/5/12 10:38
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @PostMapping("/spubounds/save")
    R saveSpuBound(@RequestBody SpuBoundTO spuBoundTO);

    @PostMapping("/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTO skuReductionTO);

}
