package com.chenyi.mall.seckill.controller;

import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.common.utils.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenyi
 * @className SecKillController
 * @date 2022/9/30 17:52
 */
@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Resource
    RedisUtils<String, Object> redisUtils;

    @GetMapping("/")
    public R test() {
        return R.ok();
    }

}
