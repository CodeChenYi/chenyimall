package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.mapper.AttrGroupMapper;
import com.chenyi.gulimall.product.service.AttrGroupService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

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
            String key = (String) map.get("key");
            QueryWrapper<AttrGroupEntity> wrapper =
                    new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId);
            if (!StringUtils.isEmpty(key)) {
                wrapper.and(obj -> obj.eq("attr_group_name", key).or().likeRight("attr_group_id", key));
            }
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(map), wrapper);
            return new PageUtils(page);
        }
    }
}