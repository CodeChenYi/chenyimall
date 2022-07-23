package com.chenyi.gulimall.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenyi
 * @className LoginController
 * @date 2022/5/15 15:19
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        request.getHeader("token");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("chenyi_mall".equals(cookie.getName())) {
                    String token = cookie.getValue();

                    return "redirect:http://gulimall.com";
                }
            }
        }
        return "login";
    }

    @GetMapping("/")
    public String indexLogin(){
        return "redirect:http://auth.gulimall.com/login";
    }

}
