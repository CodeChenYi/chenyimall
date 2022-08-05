package com.chenyi.mall.product.controller;

import com.chenyi.mall.product.service.AttrService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.product.dto.AttrDTO;
import com.chenyi.mall.product.entity.AttrEntity;
import com.chenyi.mall.product.vo.AttrVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:58:32
 */
@Slf4j
@Api(tags = "商品属性")
@RestController
@RequestMapping("/attr")
public class AttrController {
    @Resource
    private AttrService attrService;

    @Value("${chenyimall.test}")
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }

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

    @ApiOperation("查询商品销售属性信息")
    @GetMapping("/list/SaleInfo")
    public R getSaleInfo(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getSaleInfo(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/list/sale/{catId}")
    public R getSaleList(@PathVariable String catId) {
        List<AttrEntity> list = attrService.getSaleList(catId);
        return R.ok().put("list", list);
    }

    @ApiOperation("根据三级分类id查询商品属性")
    @GetMapping("/list/{catId}/{attrType}")
    public R getAttrByCatId(
            @RequestParam Map<String, Object> params,
            @PathVariable String catId,
            @PathVariable Integer attrType) {
        PageUtils page = attrService.getAttrByCatId(params, catId, attrType);
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
