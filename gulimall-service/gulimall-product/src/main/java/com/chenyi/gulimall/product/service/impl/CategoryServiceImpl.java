package com.chenyi.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.gulimall.common.utils.JSONUtils;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.common.utils.Query;
import com.chenyi.gulimall.product.constant.CacheKeyName;
import com.chenyi.gulimall.product.entity.CategoryEntity;
import com.chenyi.gulimall.product.mapper.CategoryMapper;
import com.chenyi.gulimall.product.service.CategoryBrandRelationService;
import com.chenyi.gulimall.product.service.CategoryService;
import com.chenyi.gulimall.product.vo.CategoryEntityThreeVO;
import com.chenyi.gulimall.product.vo.CategoryEntityTwoVO;
import com.chenyi.gulimall.product.vo.CategoryEntityVO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

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
        categoryEntities = null;
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
     *
     * @param root 当前菜单
     * @param all  所有菜单
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

    @Override
    public List<String> getCatelogIdPath(String catelogId) {
        List<String> parentPath = getParentPath(catelogId, new ArrayList<>());
        Collections.reverse(parentPath);
        return parentPath;
    }

    private List<String> getParentPath(String catId, List<String> path) {
        // parentId默认为0
        String parentId = "0";
        path.add(catId);
        CategoryEntity category = this.getById(catId);
        if (category != null && !parentId.equals(category.getParentCid())) {
            getParentPath(category.getParentCid(), path);
        }
        return path;
    }

    @Cacheable(value = CacheKeyName.CATEGORY_CACHE, key = "#root.methodName", sync = true)
    @Override
    public List<CategoryEntity> getCategoryLevelOne() {
        String ParentId = "0";
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", ParentId));
    }

    @Override
    public Map<String, List<CategoryEntityTwoVO>> categoryLevelJson() {
        String categoryLevelJson = stringRedisTemplate.opsForValue()
                .get(CacheKeyName.CATEGORY_CACHE + CacheKeyName.DOUBLE_COLON + "categoryLevelJson");
        if (StringUtils.isEmpty(categoryLevelJson)) {
            return categoryLevelJsonLock();
        }
        return JSONUtils.parseObject(categoryLevelJson, new TypeReference<Map<String, List<CategoryEntityTwoVO>>>() {
        });
    }

    private Map<String, List<CategoryEntityTwoVO>> categoryLevelJsonLock() {
        RLock lock = redissonClient.getLock("categoryLevelJson-lock");
        //  添加锁
        lock.lock(30, TimeUnit.SECONDS);
        Map<String, List<CategoryEntityTwoVO>> categoryLevelJsonForDb = null;
        try {
            log.debug("获取到锁");
            String categoryLevelJson = stringRedisTemplate
                    .opsForValue().get(CacheKeyName.CATEGORY_CACHE + CacheKeyName.DOUBLE_COLON + "categoryLevelJson");
            // 再次确定redis中是否有数据有就直接返回
            if (!StringUtils.isEmpty(categoryLevelJson)) {
                log.debug("缓存中已添加直接返回");
                JSONUtils.parseObject(categoryLevelJson, new TypeReference<Map<String, List<CategoryEntityTwoVO>>>() {
                });
            }
            // 查询数据库数据
            categoryLevelJsonForDb = categoryLevelJsonForDb();
            stringRedisTemplate.opsForValue()
                    .set(CacheKeyName.CATEGORY_CACHE + CacheKeyName.DOUBLE_COLON + "categoryLevelJson", JSONUtils.toJSONString(categoryLevelJsonForDb));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return categoryLevelJsonForDb;
    }

    private Map<String, List<CategoryEntityTwoVO>> categoryLevelJsonForDb() {
        log.debug("查询数据库");
        // 获取一级分类
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);

        // 筛选一级分类
        List<CategoryEntity> categoryLevelOne = getParentId(categoryEntityList, "0");

        // 收集一级分类数据
        Map<String, List<CategoryEntityTwoVO>> categoryLevelJson = categoryLevelOne.stream().collect(Collectors.toMap(k -> k.getCatId(), v -> {
            // 查询所有二级分类
            List<CategoryEntity> categoryEntities = getParentId(categoryEntityList, v.getCatId());
            // 收集二级分类
            List<CategoryEntityTwoVO> categoryEntityTwoVOList = null;
            if (categoryEntities != null) {
                categoryEntityTwoVOList = categoryEntities.stream()
                        .map(l2 -> {
                            // 封装二级分类
                            CategoryEntityTwoVO categoryEntityTwoVO = new CategoryEntityTwoVO(l2.getName(), l2.getCatId(), l2.getParentCid(), null);
                            // 查询三级分类
                            List<CategoryEntity> categoryEntitiesThree =
                                    getParentId(categoryEntityList, l2.getCatId());
                            if (categoryEntitiesThree != null) {
                                // 封装三级分类
                                List<CategoryEntityThreeVO> categoryEntityThreeVOList = categoryEntitiesThree.stream().map(l3 -> new CategoryEntityThreeVO(l3.getName(), l3.getCatId(), l3.getParentCid())).collect(Collectors.toList());
                                categoryEntityTwoVO.setCategoryEntityThreeVOList(categoryEntityThreeVOList);
                            }
                            return categoryEntityTwoVO;
                        })
                        .collect(Collectors.toList());
            }
            return categoryEntityTwoVOList;
        }));


        return categoryLevelJson;
    }

    /**
     * 获取父分类
     */
    private List<CategoryEntity> getParentId(List<CategoryEntity> categoryEntityList, String parentId) {
        return categoryEntityList.stream().filter(item -> item.getParentCid().equals(parentId)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryEntityVO> listTreeByName(String treeName) {
        // 添加查询条件
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", treeName);
        List<CategoryEntity> categoryEntities = baseMapper.selectList(wrapper);
        // copy到vo对象中
        List<CategoryEntityVO> categoryEntityVOList = new ArrayList<>(categoryEntities.size());
        categoryEntities.forEach(category -> {
            CategoryEntityVO categoryEntityVO = new CategoryEntityVO();
            BeanUtils.copyProperties(category, categoryEntityVO);
            categoryEntityVOList.add(categoryEntityVO);
        });
        // 将查询对象设置为null
        categoryEntities = null;
        return categoryEntityVOList;
    }

    @Transactional
    @Override
    public void updateDetail(CategoryEntity category) {
        baseMapper.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }
}
