package com.chenyi.mall.ware.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.mall.ware.entity.WareOrderTaskEntity;
import com.chenyi.mall.ware.service.WareOrderTaskService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;

import javax.annotation.Resource;


/**
 * 库存工作单
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:13:30
 */
@RestController
@RequestMapping("/wareordertask")
public class WareOrderTaskController {
    @Resource
    private WareOrderTaskService wareOrderTaskService;

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("ware:wareordertask:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareOrderTaskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    // @RequiresPermissions("ware:wareordertask:info")
    public R info(@PathVariable("id") Long id){
		WareOrderTaskEntity wareOrderTask = wareOrderTaskService.getById(id);

        return R.ok().put("wareOrderTask", wareOrderTask);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("ware:wareordertask:save")
    public R save(@RequestBody WareOrderTaskEntity wareOrderTask){
		wareOrderTaskService.save(wareOrderTask);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("ware:wareordertask:update")
    public R update(@RequestBody WareOrderTaskEntity wareOrderTask){
		wareOrderTaskService.updateById(wareOrderTask);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("ware:wareordertask:delete")
    public R delete(@RequestBody Long[] ids){
		wareOrderTaskService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
