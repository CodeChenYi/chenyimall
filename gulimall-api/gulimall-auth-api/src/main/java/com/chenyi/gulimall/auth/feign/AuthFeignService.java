package com.chenyi.gulimall.auth.feign;

import com.chenyi.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenyi
 * @className AuthFeignService
 * @date 2022/6/29 21:40
 */
@FeignClient("gulimall-auth")
public interface AuthFeignService {
    @GetMapping("/verifyToken")
    R verifyToken(@RequestParam String token);
}
