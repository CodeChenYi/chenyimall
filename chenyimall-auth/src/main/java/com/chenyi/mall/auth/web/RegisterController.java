package com.chenyi.mall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className RegisterController
 * @date 2022/5/15 15:27
 */
@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
