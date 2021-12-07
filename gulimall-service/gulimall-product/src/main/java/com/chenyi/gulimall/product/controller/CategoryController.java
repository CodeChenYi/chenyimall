package com.chenyi.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.chenyi.gulimall.product.vo.CategoryEntityVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品三级分类
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */

@Api(tags = "商品三级分类")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list/tree")
    public R listTree() {
        List<CategoryEntityVO> categoryEntityList = categoryService.listWithTree();
        return R.ok().put("categoryEntityList", categoryEntityList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    // @RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    // @RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    // @RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
