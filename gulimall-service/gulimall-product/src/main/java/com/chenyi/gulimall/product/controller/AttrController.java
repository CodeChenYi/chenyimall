package com.chenyi.gulimall.product.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.dto.AttrDTO;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.service.AttrService;
import com.chenyi.gulimall.product.vo.AttrVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


/**
 * 商品属性
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Api(tags = "商品属性")
@RestController
@RequestMapping("/attr")
public class AttrController {
    @Resource
    private AttrService attrService;

    /**
     * 列表
     */
    @ApiOperation("查询商品属性分页信息")
    @GetMapping("/list")
    // @RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    @ApiOperation("根据三级分类id查询商品属性")
    @GetMapping("/list/{catId}")
    public R getAttrByCatId(@RequestParam Map<String, Object> params, @PathVariable String catId) {
        PageUtils page = attrService.getAttrByCatId(params, catId);
        return R.ok().put("page", page);
    }

    @ApiOperation("修改商品属性启用状态")
    @PutMapping("/updateEnableState")
    public R updateEnableState(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);
        return R.ok();
    }

    @ApiOperation("修改搜索状态")
    @PutMapping("/updateSearchType")
    public R updateSearchType(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);
        return R.ok();
    }

    @ApiOperation("修改展示状态")
    @PutMapping("/updateShowDescState")
    public R updateShowDescState(@RequestBody AttrEntity attr) {
        attrService.updateById(attr);
        return R.ok();
    }

    /**
     * 信息
     */
    @ApiOperation("通过id获取商品信息")
    @GetMapping("/info/{attrId}")
    // @RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") String attrId){
		AttrVO attr = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @ApiOperation("保存商品详细信息")
    @PostMapping("/save")
    // @RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrDTO attr){
		attrService.saveDetail(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改商品详细信息")
    @PutMapping("/update")
    // @RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrDTO attrDTO){
		attrService.updateDetail(attrDTO);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除商品详细信息")
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody String[] attrIds){
		attrService.removeDetail(Arrays.asList(attrIds));
        return R.ok();
    }

}
