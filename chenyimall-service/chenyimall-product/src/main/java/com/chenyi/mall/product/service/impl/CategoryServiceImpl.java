package com.chenyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.to.RedisData;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.Query;
import com.chenyi.mall.common.utils.RedisUtils;
import com.chenyi.mall.product.constant.CacheKeyName;
import com.chenyi.mall.product.entity.CategoryEntity;
import com.chenyi.mall.product.mapper.CategoryMapper;
import com.chenyi.mall.product.service.CategoryBrandRelationService;
import com.chenyi.mall.product.service.CategoryService;
import com.chenyi.mall.product.vo.CategoryEntityOneVO;
import com.chenyi.mall.product.vo.CategoryEntityThreeVO;
import com.chenyi.mall.product.vo.CategoryEntityTwoVO;
import com.chenyi.mall.product.vo.CategoryEntityVO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements CategoryService {

    @Resource
    private RedisUtils<String, Object> redisUtils;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Resource
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
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

    /**
     * 获取数据，如果没有那么加锁获取
     *
     * @return
     */
    @Override
    public Map<String, List<CategoryEntityOneVO>> categoryLevelJson() {
        // 判断key是否过期
        boolean flag = redisUtils.keyIsExpired(CacheKeyName.CATEGORY_CACHE
                + "categoryLevelJson");
        if (flag) {
            // 加锁，异步查询数据
            return categoryLevelJsonLock();
        }
        return (Map<String, List<CategoryEntityOneVO>>) redisUtils.getRedisData(
                CacheKeyName.CATEGORY_CACHE
                + CacheKeyName.CATEGORY_LEVEL_JSON_KEY);
    }



    /**
     * 如果数据已经过期，那么获取锁后，开启一个异步线程去更新数据库，主线程直接返回
     * 其他线程访问如果获取不到锁，代表缓存已经在修改当中，也直接进行返回
     * 等待异步线程更新完毕那么就是最新数据
     * @return
     */
    private Map<String, List<CategoryEntityOneVO>> categoryLevelJsonLock() {
        RLock lock = redissonClient.getLock(CacheKeyName.CATEGORY_CACHE
                + CacheKeyName.CATEGORY_LEVEL_JSON_LOCK_KEY);
        try {
            // 尝试获取到锁如果没有获取到锁直接返回
            boolean flag = lock.tryLock(ChenYiMallConstant.NO_EXPIRATION_TIME_VALUE,
                    ChenYiMallConstant.THIRTY_SECONDS_VALUE,
                    TimeUnit.SECONDS);
            if (!flag) {
                // 没有获取到锁直接返回
                log.debug("=================未获取到锁直接返回旧数据==============");
                return (Map<String, List<CategoryEntityOneVO>>) redisUtils.getRedisData(
                        CacheKeyName.CATEGORY_CACHE
                        + CacheKeyName.CATEGORY_LEVEL_JSON_KEY);
            }
            log.debug("=========categoryLevelJsonLock：获取到锁==============");
            // 开启异步线程
            CompletableFuture.runAsync(() -> {
                log.debug("==================异步查询数据库中==================");
                // 查询数据库数据，并封装到RedisData中
                RedisData<Map<String, List<CategoryEntityOneVO>>> mapRedisData
                        = new RedisData<>(categoryLevelJsonForDb(),
                        LocalDateTime.now().plusDays(ChenYiMallConstant.SEVEN_DAYS_VALUE));
                // 设置到缓存中
                redisUtils.setString(
                        CacheKeyName.CATEGORY_CACHE +
                                CacheKeyName.CATEGORY_LEVEL_JSON_KEY,
                                mapRedisData);
                // 让异步线程去解锁
                lock.unlock();
            }, executor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 主线程直接返回
        return (Map<String, List<CategoryEntityOneVO>>) redisUtils.getRedisData(CacheKeyName.CATEGORY_CACHE
                + CacheKeyName.CATEGORY_LEVEL_JSON_KEY);
    }

    private Map<String, List<CategoryEntityOneVO>> categoryLevelJsonForDb() {
        log.debug("=================查询数据库==================");
        // 获取所有分类，只用一个sql查询，使用streamAPI来进行数据过滤
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);

        // 筛选一级分类
        String parentId = "0";
        List<CategoryEntity> categoryLevelOne = getParentId(categoryEntityList, parentId);

        // 收集一级分类数据
        Map<String, List<CategoryEntityOneVO>> categoryLevelJson = categoryLevelOne
                .stream().collect(Collectors.toMap(k -> k.getCatId(), v -> {
            // 查询所有二级分类
            List<CategoryEntity> categoryEntities = getParentId(categoryEntityList, v.getCatId());
            // 生成一级分类
            List<CategoryEntityOneVO> categoryEntityOneVOList = new ArrayList<>(categoryLevelOne.size());
            CategoryEntityOneVO categoryEntityOneVO = new CategoryEntityOneVO(v.getName(), v.getCatId(), null);
            categoryEntityOneVOList.add(categoryEntityOneVO);
            // 收集二级分类
            List<CategoryEntityTwoVO> categoryEntityTwoVOList = null;
            if (categoryEntities != null) {
                categoryEntityTwoVOList = categoryEntities.stream().map(l2 -> {
                            // 封装二级分类
                            CategoryEntityTwoVO categoryEntityTwoVO = new CategoryEntityTwoVO(l2.getName(), l2.getCatId(), l2.getParentCid(), null);
                            // 查询三级分类
                            List<CategoryEntity> categoryEntitiesThree =
                                    getParentId(categoryEntityList, l2.getCatId());
                            if (categoryEntitiesThree != null) {
                                // 封装三级分类
                                List<CategoryEntityThreeVO> categoryEntityThreeVOList = categoryEntitiesThree
                                        .stream()
                                        .map(l3 -> new CategoryEntityThreeVO(l3.getName(), l3.getCatId(), l3.getParentCid()))
                                        .collect(Collectors.toList());
                                categoryEntityTwoVO.setCategoryEntityThreeVOList(categoryEntityThreeVOList);
                            }
                            return categoryEntityTwoVO;
                        }).collect(Collectors.toList());
                categoryEntityOneVO.setCategoryEntityTwoVO(categoryEntityTwoVOList);
            }
            return categoryEntityOneVOList;
        }));
        return categoryLevelJson;
    }

    /**
     * 获取父分类
     */
    private List<CategoryEntity> getParentId(List<CategoryEntity> categoryEntityList, String parentId) {
        return categoryEntityList.stream()
                .filter(item -> item.getParentCid().equals(parentId))
                .collect(Collectors.toList());
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(CategoryEntity category) {
        baseMapper.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }
}
