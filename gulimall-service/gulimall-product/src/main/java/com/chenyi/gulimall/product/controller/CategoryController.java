package com.chenyi.gulimall.product.controller;

import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.domain.Category;
import com.chenyi.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author by chenyi
 * @className CategoryController
 * @date 2021/9/25 18:39
 */

@Api(tags = "三级分类")
@RequestMapping("/product/category")
@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("添加三级分类")
    @PostMapping("/addCategory")
    public R addCategory(@RequestBody Category category) {
        boolean b = categoryService.save(category);
        return R.isOk(b);
    }


}
