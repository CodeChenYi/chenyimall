package com.chenyi.mall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className OrderWebController
 * @date 2022/6/30 15:20
 */
@Controller
public class OrderWebController {

    @GetMapping(value = {"/"})
    public String index() {
        return "index";
    }
}
