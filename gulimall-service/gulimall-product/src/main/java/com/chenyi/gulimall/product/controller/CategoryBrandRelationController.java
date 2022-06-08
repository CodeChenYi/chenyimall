package com.chenyi.gulimall.product.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.entity.CategoryBrandRelationEntity;
import com.chenyi.gulimall.product.service.CategoryBrandRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 品牌分类关联
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Api(tags = "品牌分类关联")
@RestController
@RequestMapping("/categorybrandrelation")
public class CategoryBrandRelationController {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @ApiOperation("品牌分类关联")
    @GetMapping("/list")
    // @RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("根据id查询品牌分类关联")
    @GetMapping("/info/{id}")
    // @RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") String id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    @ApiOperation("根据三级分类id查询品牌信息")
    @GetMapping("/category/{catId}")
    public R getCategoryBrandByCatId(@PathVariable String catId) {
        List<CategoryBrandRelationEntity> brandEntities =
                categoryBrandRelationService.getCategoryBrandByCatId(catId);
        return R.ok().put("list", brandEntities);
    }

    @ApiOperation("根据品牌id查询分组关系信息")
    @GetMapping("/infoByBrandId/{brandId}")
    public R infoByBrandId(@PathVariable("brandId") String brandId) {
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.infoByBrandId(brandId);
        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @ApiOperation("品牌分类关联新增")
    @PostMapping("/save")
    // @RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        categoryBrandRelationService.saveCategoryBrandRelation(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("品牌分类关联修改")
    @PutMapping("/update")
    // @RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("品牌分类关联删除")
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody String[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
