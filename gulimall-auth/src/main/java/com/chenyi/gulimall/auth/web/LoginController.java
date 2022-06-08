package com.chenyi.gulimall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chenyi
 * @className LoginController
 * @date 2022/5/15 15:19
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String indexLogin(){
        return "redirect:http://auth.gulimall.com/login";
    }

}
