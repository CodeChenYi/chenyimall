package com.chenyi.gulimall.product.controller;

import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.R;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.service.AttrGroupService;
import com.chenyi.gulimall.product.service.AttrService;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.AttrGroupEntityVO;
import com.chenyi.gulimall.product.vo.AttrGroupWithAttrsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "三级分类属性分组")
@RestController
@RequestMapping("/attrgroup")
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private AttrService attrService;

    @GetMapping("/listPage/{catelogId}")
    public R listCateLogById(@RequestParam Map<String, Object> map, @PathVariable String catelogId) {
        PageUtils page = attrGroupService.queryPageById(map, catelogId);
        return R.ok().put("page", page);
    }

    @ApiOperation("根据categoryId获取分组信息")
    @GetMapping("/list/{catelogId}")
    public R listCategoryById(@PathVariable String catelogId) {
        List<AttrGroupEntity> list = attrGroupService.listCategoryById(catelogId);
        return R.ok().put("list", list);
    }

    @GetMapping("/getRelationByAttrGroupId/{attrGroupId}")
    public R getRelationByAttrGroupId(@PathVariable String attrGroupId) {
        List<AttrEntity> list = attrService.getRelationByAttrGroupId(attrGroupId);
        return R.ok().put("list", list);
    }

    @ApiOperation("获取属性分组信息和商品信息")
    @GetMapping("/getAttrGroupWithAttrByCategoryId/{catId}")
    public R getAttrGroupWithAttrByCategoryId(@PathVariable String catId) {
        List<AttrGroupWithAttrsVO> list = attrGroupService.getAttrGroupWithAttrByCategoryId(catId);
        return R.ok().put("list", list);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrGroupService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    // @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") String attrGroupId) {
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
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    // @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    // @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody String[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
