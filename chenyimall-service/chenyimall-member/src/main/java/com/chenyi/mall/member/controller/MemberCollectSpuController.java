package com.chenyi.mall.member.controller;

import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.member.entity.MemberCollectSpuEntity;
import com.chenyi.mall.member.service.MemberCollectSpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 会员收藏的商品
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:10:10
 */
@RestController
@RequestMapping("/membercollectspu")
public class MemberCollectSpuController {
    @Resource
    private MemberCollectSpuService memberCollectSpuService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("member:membercollectspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberCollectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("member:membercollectspu:info")
    public R info(@PathVariable("id") Long id){
		MemberCollectSpuEntity memberCollectSpu = memberCollectSpuService.getById(id);

        return R.ok().put("memberCollectSpu", memberCollectSpu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("member:membercollectspu:save")
    public R save(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.save(memberCollectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("member:membercollectspu:update")
    public R update(@RequestBody MemberCollectSpuEntity memberCollectSpu){
		memberCollectSpuService.updateById(memberCollectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("member:membercollectspu:delete")
    public R delete(@RequestBody Long[] ids){
		memberCollectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
