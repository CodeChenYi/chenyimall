package com.chenyi.mall.api.coupon.feign;

import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.api.product.to.SkuReductionTO;
import com.chenyi.mall.api.product.to.SpuBoundTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenyi
 * @className CouponFeignService
 * @date 2022/5/12 10:38
 */
@FeignClient("chenyimall-coupon")
public interface CouponFeignService {

    @PostMapping("/spubounds/save")
    R saveSpuBound(@RequestBody SpuBoundTO spuBoundTO);

    @PostMapping("/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTO skuReductionTO);

    /**
     * 通过优惠券和会员id获取优惠券信息
     * @param memberId
     * @param couponId
     * @return
     */
    @GetMapping("/")
    R getCouponInfoByMemberId(@RequestParam Long memberId, @RequestParam Long couponId);

}
