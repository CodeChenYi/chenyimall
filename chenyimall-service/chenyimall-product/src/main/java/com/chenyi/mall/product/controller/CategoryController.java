package com.chenyi.mall.product.controller;

import com.chenyi.mall.product.service.CategoryService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.product.entity.CategoryEntity;
import com.chenyi.mall.product.vo.CategoryEntityVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

    @ApiOperation("查询三级分类")
    @GetMapping("/list/tree")
    public R listTree() {
        List<CategoryEntityVO> categoryEntityList = categoryService.listWithTree();
        return R.ok().put("data", categoryEntityList);
    }

    @ApiOperation("按名称查询三级分类")
    @GetMapping("/listTreeByName")
    public R listTreeByName(@RequestParam String treeName) {
        List<CategoryEntityVO> categoryEntityList = categoryService.listTreeByName(treeName);
        return R.ok().put("data", categoryEntityList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public R info(@PathVariable("catId") String catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改排序
     */
    @PutMapping("/update/sort")
    public R updateSort(@RequestBody List<CategoryEntity> category){
        categoryService.updateBatchById(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateDetail(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody String[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
