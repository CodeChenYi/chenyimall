package com.chenyi.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.gulimall.common.utils.PageUtils;
import com.chenyi.gulimall.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:10:10
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

