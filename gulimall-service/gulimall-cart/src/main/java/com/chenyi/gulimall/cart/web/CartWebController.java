package com.chenyi.gulimall.cart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className CartController
 * @date 2022/6/29 16:37
 */
@Controller
public class CartWebController {

    @GetMapping(value = {"cart.html", "/"})
    public String cartList() {
        return "cartList";
    }

    @GetMapping(value = "cartSuccess.html")
    public String cartSuccess() {
        return "success";
    }

}
