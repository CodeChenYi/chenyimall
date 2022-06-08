package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.constant.GuliMallConstant;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.chenyi.gulimall.product.mapper.AttrGroupMapper;
import com.chenyi.gulimall.product.mapper.AttrMapper;
import com.chenyi.gulimall.product.service.AttrGroupService;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.AttrGroupEntityVO;
import com.chenyi.gulimall.product.vo.AttrGroupWithAttrsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroupEntity> implements AttrGroupService {

    @Resource
    private CategoryService categoryService;

    @Resource
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Resource
    private AttrMapper attrMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        log.debug("========================={}", params);
        String key = (String) params.get(GuliMallConstant.STRING_KEY);
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        IPage<AttrGroupEntity> page;
        if (!GuliMallConstant.NULL_STRING.equals(key)) {
            wrapper.likeRight("attr_group_name", key);
        }
        page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                wrapper
        );
        PageUtils pageUtils = new PageUtils(page);

        List<AttrGroupEntity> records = page.getRecords();
        // 通过id查询三级分类名称
        List<AttrGroupEntityVO> attrGroupEntityVOS = records.stream().map(attrGroupEntity -> {
            AttrGroupEntityVO attrGroupEntityVO = new AttrGroupEntityVO();
            BeanUtils.copyProperties(attrGroupEntity, attrGroupEntityVO);

            String catelogId = attrGroupEntity.getCatelogId();
            CategoryEntity category = categoryService.getById(catelogId);
            attrGroupEntityVO.setCategoryName(category.getName());
            return attrGroupEntityVO;
        }).collect(Collectors.toList());

        pageUtils.setList(attrGroupEntityVOS);
        return pageUtils;
    }

    @Override
    public PageUtils queryPageById(Map<String, Object> map, String catelogId) {
        // 根据categoryId进行查询
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelog_id", catelogId);
        IPage<AttrGroupEntity> page =
                this.page(new Query<AttrGroupEntity>().getPage(map), wrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<AttrGroupEntity> records = page.getRecords();
        List<AttrGroupEntityVO> attrGroupEntityVOS = records.stream().map(attrGroupEntity -> {
            AttrGroupEntityVO attrGroupEntityVO = new AttrGroupEntityVO();
            BeanUtils.copyProperties(attrGroupEntity, attrGroupEntityVO);

            CategoryEntity category = categoryService.getById(catelogId);
            attrGroupEntityVO.setCategoryName(category.getName());
            return attrGroupEntityVO;
        }).collect(Collectors.toList());

        pageUtils.setList(attrGroupEntityVOS);
        return pageUtils;
    }

    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrByCategoryId(String catId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelog_id", catId);
        List<AttrGroupEntity> attrGroupEntities = baseMapper.selectList(wrapper);

        return attrGroupEntities.stream()
                .map(attrGroupEntity -> {
                    AttrGroupWithAttrsVO attrGroupWithAttrsVO = new AttrGroupWithAttrsVO();
                    BeanUtils.copyProperties(attrGroupEntity, attrGroupWithAttrsVO);

                    // 更具分组id查询中间表信息
                    String attrGroupId = attrGroupEntity.getAttrGroupId();
                    QueryWrapper<AttrAttrgroupRelationEntity> attrGroupWrapper = new QueryWrapper<>();
                    attrGroupWrapper.eq("attr_group_id", attrGroupId);
                    List<AttrAttrgroupRelationEntity> relationEntities
                            = attrAttrgroupRelationMapper.selectList(attrGroupWrapper);

                    // 判断当前中间表信息是否为空
                    if (relationEntities != null && relationEntities.size() > 0) {
                        List<String> AttrIdList = relationEntities.stream()
                                // 获取AttrId
                                .map(AttrAttrgroupRelationEntity::getAttrId)
                                // 过滤空的AttrId
                                .filter(attrId -> !StringUtils.isEmpty(attrId))
                                .collect(Collectors.toList());
                        // 查询商品信息
                        List<AttrEntity> attrEntities = attrMapper.selectBatchIds(AttrIdList);
                        attrGroupWithAttrsVO.setAttrEntityList(attrEntities);
                    }

                    return attrGroupWithAttrsVO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<AttrGroupEntity> listCategoryById(String catelogId) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("catelog_id", catelogId);
        return baseMapper.selectList(wrapper);
    }

}
