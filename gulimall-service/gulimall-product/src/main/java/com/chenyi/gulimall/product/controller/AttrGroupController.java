package com.chenyi.gulimall.product.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.service.AttrGroupService;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.AttrGroupEntityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 属性分组
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
@RestController
@RequestMapping("/attrgroup")
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list/{catelogId}")
    public R listCateLogById(@RequestParam Map<String, Object> map, @PathVariable String catelogId) {
        PageUtils page = attrGroupService.queryPageById(map, catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    // @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") String attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        AttrGroupEntityVO attrGroupEntityVO = new AttrGroupEntityVO();
        if (attrGroup != null) {
            List<String> catelogPath = categoryService.getCatelogIdPath(attrGroup.getCatelogId());
            BeanUtils.copyProperties(attrGroup, attrGroupEntityVO);
            attrGroupEntityVO.setCatelogIdPath(catelogPath);
        }
        return R.ok().put("attrGroup", attrGroupEntityVO);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody String[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}