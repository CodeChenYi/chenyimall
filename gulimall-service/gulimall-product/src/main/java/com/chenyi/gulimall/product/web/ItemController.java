package com.chenyi.gulimall.product.web;

import com.chenyi.gulimall.common.utils.JSONUtils;
import com.chenyi.gulimall.product.service.SkuInfoService;
import com.chenyi.gulimall.product.vo.SkuItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author chenyi
 * @className ItemController
 * @date 2022/5/14 14:15
 */
@Slf4j
@Controller
public class ItemController {

    @Resource
    SkuInfoService skuInfoService;

    @GetMapping("/item/{skuId}.html")
    public String getItem(@PathVariable String skuId, Model model) {
        log.info("=================================================");
        SkuItemVO skuItemVO = skuInfoService.getItem(skuId);
        String s = JSONUtils.toJSONString(skuItemVO);
        log.info("skuItem:{}", s);
        if (skuItemVO.getSkuInfo() != null) {
            model.addAttribute("item", skuItemVO);
            return "item";
        } else {
            return "redirect:http://gulimall.com";
        }
    }

}