package com.chenyi.gulimall.auth.controller;

import com.chenyi.gulimall.auth.service.AuthTokenService;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.member.to.MemberInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenyi
 * @className AuthController
 * @date 2022/5/16 0:04
 */
@Slf4j
@Api("获取认证授权")
@RestController
public class AuthController {

    @Resource
    private AuthTokenService authTokenService;

    @ApiOperation("获取认证token")
    @PostMapping("/token")
    public R token(@RequestParam String userName, @RequestParam String password) {
        String token = authTokenService.infoByUserName(userName, password);
        return R.ok().put("token", token);
    }

    @ApiOperation("验证是否登录")
    @GetMapping("/verifyToken")
    public R verifyToken(@RequestParam String token) {
        MemberInfo memberInfo = authTokenService.verifyToken(token);
        return R.ok().put("memberInfo", memberInfo);
    }

    @GetMapping("/test")
    public R test() {
        return R.ok();
    }

}
