package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.mapper.CategoryMapper;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.CategoryEntityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntityVO> listWithTree() {
        // 查询所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        // 转换分类对象
        List<CategoryEntityVO> categoryEntityVOList = new ArrayList<>(categoryEntities.size());
        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryEntityVO categoryEntityVO = new CategoryEntityVO();
            BeanUtils.copyProperties(categoryEntity, categoryEntityVO);
            categoryEntityVOList.add(categoryEntityVO);
        }

        // 递归查询菜单数据
        return categoryEntityVOList.stream()
                .filter(categoryEntity -> "0".equals(categoryEntity.getParentCid()))
                // 递归查询子菜单
                .peek(categoryEntityVO -> categoryEntityVO.setCategoryEntityChildrenList(getChildrens(categoryEntityVO, categoryEntityVOList)))
                // 排序
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    /**
     * 获取子菜单
     * @param root 当前菜单
     * @param all 所有菜单
     * @return
     */
    private List<CategoryEntityVO> getChildrens(CategoryEntityVO root, List<CategoryEntityVO> all) {
        return all.stream()
                .filter(categoryEntityVO -> categoryEntityVO.getParentCid().equals(root.getCatId()))
                // 查询子菜单
                .peek(categoryEntityVO -> categoryEntityVO.setCategoryEntityChildrenList(getChildrens(categoryEntityVO, all)))
                // 排序
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }
}
