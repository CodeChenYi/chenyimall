package com.chenyi.mall.auth.feign;

import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenyi
 * @className AuthFeignService
 * @date 2022/6/29 21:40
 */
@FeignClient("mall-auth")
public interface AuthFeignService {
    @GetMapping("/verifyToken")
    R verifyToken(@RequestParam String token);
}
