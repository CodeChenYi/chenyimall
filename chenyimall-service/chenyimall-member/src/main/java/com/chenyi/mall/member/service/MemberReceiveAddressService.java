package com.chenyi.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.member.entity.MemberReceiveAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:10:10
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据会员id查询地址信息
     * @param memberId
     * @return
     */
    List<MemberReceiveAddressEntity> getInfoByMemberId(Long memberId);
}

