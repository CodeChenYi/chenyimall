package com.chenyi.mall.auth.controller;

import com.chenyi.mall.auth.service.AuthTokenService;
import com.chenyi.mall.auth.vo.MemberVO;
import com.chenyi.mall.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取用户信息")
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(@RequestParam String token) {
        MemberVO member = authTokenService.getMemberInfo(token);
        return R.ok().put("userInfo", member);
    }

    @GetMapping("/test")
    public R test() {
        return R.ok();
    }

}
