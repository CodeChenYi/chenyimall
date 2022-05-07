package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.mapper.AttrGroupMapper;
import com.chenyi.gulimall.product.mapper.AttrMapper;
import com.chenyi.gulimall.product.mapper.CategoryMapper;
import com.chenyi.gulimall.product.service.AttrAttrgroupRelationService;
import com.chenyi.gulimall.product.service.AttrGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Resource
    private AttrMapper attrMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        log.debug("=========================" + params);
        String key = (String) params.get("key");

        IPage<AttrGroupEntity> page;
        if ("".equals(key)) {
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    new QueryWrapper<AttrGroupEntity>()
            );
        } else {
            QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
            wrapper.likeRight("attr_group_name", key);
            page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
        }

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageById(Map<String, Object> map, String catelogId) {
        String getAll = "0";
        // 如果catelogId等于0那么就查询全部
        if (getAll.equals(catelogId)) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(map), new QueryWrapper<>());
            return new PageUtils(page);
        } else {
            // 按照条件查询
            QueryWrapper<AttrGroupEntity> wrapper =
                    new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
            String key = (String) map.get("key");
            if (!StringUtils.isEmpty(key)) {
                wrapper.and(obj -> obj.eq("attr_group_name", key).or().likeRight("attr_group_id", key));
            }
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(map), wrapper);
            return new PageUtils(page);
        }
    }

}
