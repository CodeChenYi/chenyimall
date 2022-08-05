package com.chenyi.mall.api.product.feign;

import com.chenyi.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author chenyi
 * @className ProductFeignService
 * @date 2022/6/29 20:56
 */
@FeignClient("chenyimall-product")
public interface ProductFeignService {

    @RequestMapping("/skuinfo/info/{skuId}")
    R info(@PathVariable("skuId") String skuId);

    @GetMapping("/skusaleattrvalue/getSaleAttrBySkuId")
    List<String> getSaleAttrBySkuId(@RequestParam String skuId);

    @GetMapping("/skuinfo/getSkuPriceById")
    R getSkuPriceById(@RequestParam List<String> skuId);
}
