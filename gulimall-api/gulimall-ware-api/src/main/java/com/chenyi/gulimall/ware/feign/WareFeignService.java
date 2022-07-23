package com.chenyi.gulimall.ware.feign;

import com.chenyi.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author chenyi
 * @className WareFeignService
 * @date 2022/7/1 17:07
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {

    @GetMapping("/waresku/infoBySkuId/{skuId}")
    R infoBySkuId(@PathVariable List<String> skuId);

}