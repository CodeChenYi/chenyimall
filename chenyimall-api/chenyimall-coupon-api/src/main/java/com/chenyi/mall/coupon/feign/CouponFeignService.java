package com.chenyi.mall.coupon.feign;

import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.product.to.SkuReductionTO;
import com.chenyi.mall.product.to.SpuBoundTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chenyi
 * @className CouponFeignService
 * @date 2022/5/12 10:38
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @PostMapping("/spubounds/save")
    R saveSpuBound(@RequestBody SpuBoundTO spuBoundTO);

    @PostMapping("/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTO skuReductionTO);

}
