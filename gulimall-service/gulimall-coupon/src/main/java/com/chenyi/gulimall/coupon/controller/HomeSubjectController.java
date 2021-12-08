package com.chenyi.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.coupon.entity.HomeSubjectEntity;
import com.chenyi.gulimall.coupon.service.HomeSubjectService;
import com.chenyi.gulimall.common.utils.PageUtils;

import javax.annotation.Resource;
import com.chenyi.gulimall.common.utils.R;



/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author chenyi
 * @className  HomeSubject
 * @date 2021-12-07 01:27:49
 */
@RestController
@RequestMapping("/homesubject")
public class HomeSubjectController {
    @Resource
    private HomeSubjectService homeSubjectService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("coupon:homesubject:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeSubjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("coupon:homesubject:info")
    public R info(@PathVariable("id") Long id){
		HomeSubjectEntity homeSubject = homeSubjectService.getById(id);

        return R.ok().put("homeSubject", homeSubject);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("coupon:homesubject:save")
    public R save(@RequestBody HomeSubjectEntity homeSubject){
		homeSubjectService.save(homeSubject);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("coupon:homesubject:update")
    public R update(@RequestBody HomeSubjectEntity homeSubject){
		homeSubjectService.updateById(homeSubject);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("coupon:homesubject:delete")
    public R delete(@RequestBody Long[] ids){
		homeSubjectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
