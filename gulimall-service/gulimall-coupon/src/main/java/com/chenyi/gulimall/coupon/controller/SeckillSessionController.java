package com.chenyi.gulimall.coupon.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.coupon.entity.SeckillSessionEntity;
import com.chenyi.gulimall.coupon.service.SeckillSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 秒杀活动场次
 *
 * @author chenyi
 * @className SeckillSession
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/seckillsession")
public class SeckillSessionController {
    @Resource
    private SeckillSessionService seckillSessionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:seckillsession:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seckillSessionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:seckillsession:info")
    public R info(@PathVariable("id") Long id) {
        SeckillSessionEntity seckillSession = seckillSessionService.getById(id);

        return R.ok().put("seckillSession", seckillSession);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:seckillsession:save")
    public R save(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSessionService.save(seckillSession);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:seckillsession:update")
    public R update(@RequestBody SeckillSessionEntity seckillSession) {
        seckillSessionService.updateById(seckillSession);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:seckillsession:delete")
    public R delete(@RequestBody Long[] ids) {
        seckillSessionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
