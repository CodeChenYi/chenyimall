package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.enums.ResultEnum;
import com.chenyi.gulimall.common.exception.GuliMallException;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.constant.ProductConstant;
import com.chenyi.gulimall.product.dto.AttrDTO;
import com.chenyi.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.chenyi.gulimall.product.entity.AttrEntity;
import com.chenyi.gulimall.product.entity.AttrGroupEntity;
import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.mapper.AttrAttrgroupRelationMapper;
import com.chenyi.gulimall.product.mapper.AttrGroupMapper;
import com.chenyi.gulimall.product.mapper.AttrMapper;
import com.chenyi.gulimall.product.service.AttrService;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.AttrVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity> implements AttrService {

    @Resource
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private AttrGroupMapper attrGroupMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_type", ProductConstant.ATTR_BASE_TYPE.getType());
        return getAttrVOPage(params, queryWrapper);
    }

    private List<AttrVO> getAttrVO(List<AttrEntity> records) {
        return records.stream().map(attr -> {
            AttrVO attrVO = new AttrVO();
            BeanUtils.copyProperties(attr, attrVO);

            // 查询三级分类信息
            String catelogId = attr.getCatelogId();
            CategoryEntity categoryEntity = categoryService.getById(catelogId);
            if (categoryEntity != null) {
                attrVO.setCategoryName(categoryEntity.getName());
            }

            // 根据中间表查询属性分组名称
            QueryWrapper<AttrAttrgroupRelationEntity> relationWrapper = new QueryWrapper<>();
            relationWrapper.eq("attr_id", attr.getAttrId());

            AttrAttrgroupRelationEntity relation = attrAttrgroupRelationMapper.selectOne(relationWrapper);
            if (relation != null) {
                String attrGroupId = relation.getAttrGroupId();
                AttrGroupEntity attrGroupEntity = attrGroupMapper.selectById(attrGroupId);
                if (attrGroupEntity != null) {
                    attrVO.setAttrGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
            return attrVO;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveDetail(AttrDTO attrDTO) {
        // 添加商品属性
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrDTO, attrEntity);
        baseMapper.insert(attrEntity);
        log.info("=========123123==========={}", attrDTO.getAttrId());
        // 添加中间表数据
        Integer attrType = attrEntity.getAttrType();
        // 判断是否是基本属性的添加
        if (ProductConstant.ATTR_BASE_TYPE.getType().equals(attrType)) {
            AttrAttrgroupRelationEntity relation = new AttrAttrgroupRelationEntity();
            relation.setAttrId(attrEntity.getAttrId());
            relation.setAttrGroupId(attrDTO.getAttrGroupId());
            attrAttrgroupRelationMapper.insert(relation);
        }

    }

    @Override
    public AttrVO getAttrInfo(String attrId) {
        // 查询商品属性
        AttrEntity attrEntity = baseMapper.selectById(attrId);
        AttrVO attrVO = new AttrVO();

        if (attrEntity != null) {
            BeanUtils.copyProperties(attrEntity, attrVO);
            // 查询三级分类路径
            List<String> catelogIdPath = categoryService.getCatelogIdPath(attrEntity.getCatelogId());
            attrVO.setCategoryPath(catelogIdPath);

            Integer attrType = attrEntity.getAttrType();
            // 判断是否是基本属性
            if (ProductConstant.ATTR_BASE_TYPE.getType().equals(attrType)) {
                QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("attr_id", attrEntity.getAttrId());
                AttrAttrgroupRelationEntity relation = attrAttrgroupRelationMapper.selectOne(wrapper);
                attrVO.setAttrGroupId(relation.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupMapper.selectById(relation.getAttrGroupId());
                attrVO.setAttrGroupName(attrGroupEntity.getAttrGroupName());
            }
        }


        return attrVO;
    }

    @Transactional
    @Override
    public void updateDetail(AttrDTO attrDTO) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrDTO, attrEntity);
        baseMapper.updateById(attrEntity);

        Integer attrType = attrEntity.getAttrType();
        if (ProductConstant.ATTR_BASE_TYPE.getType().equals(attrType)) {
            // 修改分组中间表信息
            String attrId = attrEntity.getAttrId();
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", attrId);
            AttrAttrgroupRelationEntity relation = new AttrAttrgroupRelationEntity();
            relation.setAttrGroupId(attrDTO.getAttrGroupId());
            attrAttrgroupRelationMapper.update(relation, wrapper);
        }
    }

    @Override
    public PageUtils getAttrByCatId(Map<String, Object> params, String catId, Integer attrType) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (ProductConstant.ATTR_BASE_TYPE.getType().equals(attrType) || ProductConstant.ATTR_SALE_TYPE.getType().equals(attrType)) {
            queryWrapper.eq("attr_type", attrType);
        } else {
            throw new GuliMallException(ResultEnum.PARAMETER_VERIFICATION_ERROR.getCode(),
                    ResultEnum.PARAMETER_VERIFICATION_ERROR.getMsg());
        }
        queryWrapper.eq("catelog_id", catId);
        // 查询商品基本参数
        IPage<AttrEntity> page =
                this.page(new Query<AttrEntity>().getPage(params), queryWrapper);

        // 查询商品详细系信息
        List<AttrEntity> records = page.getRecords();
        PageUtils pageUtils = new PageUtils(page);
        List<AttrVO> attrVO = getAttrVO(records);
        pageUtils.setList(attrVO);
        return pageUtils;
    }

    @Transactional
    @Override
    public void removeDetail(List<String> asList) {
        this.removeByIds(asList);
        // 查询当前id数据
        List<AttrEntity> attrEntities = baseMapper.selectBatchIds(asList);
        // 筛选出为基本属性的记录
        List<AttrEntity> baseTypeIdList = attrEntities.stream()
                .filter(attr -> ProductConstant.ATTR_BASE_TYPE.getType().equals(attr.getAttrType())).collect(Collectors.toList());

        // 删除基本属性的关联信息
        if (baseTypeIdList.size() > 0) {
            baseTypeIdList.forEach(baseTypeAttr -> {
                QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("attr_id", baseTypeAttr.getAttrId());
                attrAttrgroupRelationMapper.delete(wrapper);
            });
        }


    }

    @Override
    public List<AttrEntity> getRelationByAttrGroupId(String attrGroupId) {
        QueryWrapper<AttrAttrgroupRelationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_group_id", attrGroupId);
        List<AttrAttrgroupRelationEntity> relation =
                attrAttrgroupRelationMapper.selectList(wrapper);
        if (relation != null && relation.size() > 0) {
            List<String> attrIdList =
                    relation.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
            return baseMapper.selectBatchIds(attrIdList);
        }
        return null;
    }

    @Override
    public PageUtils getSaleInfo(Map<String, Object> params) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        String SaleType = "0";
        String both = "2";
        wrapper.eq("attr_type", SaleType).or().eq("attr_type", both);
        return getAttrVOPage(params, wrapper);
    }

    @Override
    public List<AttrEntity> getSaleList(String catId) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("attr_type", "0");
        return baseMapper.selectList(wrapper);
    }

    private PageUtils getAttrVOPage(Map<String, Object> params, QueryWrapper<AttrEntity> wrapper) {
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.likeRight("attr_name", key);
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                wrapper);

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrVO> attrVOList = getAttrVO(records);

        pageUtils.setList(attrVOList);
        return pageUtils;
    }
}