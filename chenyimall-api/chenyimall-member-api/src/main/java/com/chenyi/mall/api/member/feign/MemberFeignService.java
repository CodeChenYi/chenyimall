package com.chenyi.mall.api.member.feign;

import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.api.member.to.MemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenyi
 * @className MemberFeignService
 * @date 2022/5/15 23:04
 */
@FeignClient("chenyimall-member")
public interface MemberFeignService {

    @PostMapping("/memberUser/infoUser")
    MemberInfo infoByUserName(@RequestParam String userName);

    @GetMapping("/memberAddress/getInfoByMemberId/{memberId}")
    R getInfoByMemberId(@PathVariable Long memberId);

    /**
     * 获取用户收货地址信息
     * @param id
     * @return
     */
    @GetMapping("/memberAddress/info/{id}")
    R info(@PathVariable Long id);
}
