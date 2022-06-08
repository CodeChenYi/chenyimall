package com.chenyi.gulimall.member.feign;

import com.chenyi.gulimall.member.to.MemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenyi
 * @className MemberFeignService
 * @date 2022/5/15 23:04
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    @PostMapping("memberUser/infoUser")
    MemberInfo infoByUserName(@RequestParam String userName);

}
