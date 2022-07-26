package com.chenyi.mall.member.controller;

import com.chenyi.mall.member.entity.MemberReceiveAddressEntity;
import com.chenyi.mall.member.service.MemberReceiveAddressService;
import com.chenyi.mall.common.utils.PageUtils;
import com.chenyi.mall.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 会员收货地址
 *
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 23:10:10
 */
@RestController
@RequestMapping("/memberAddress")
public class MemberReceiveAddressController {
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

    @GetMapping("/getInfoByMemberId/{memberId}")
    public R getInfoByMemberId(@PathVariable Long memberId) {
        List<MemberReceiveAddressEntity> memberAddressList =
                memberReceiveAddressService.getInfoByMemberId(memberId);
        return R.ok().put("memberAddressList", memberAddressList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberReceiveAddressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);

        return R.ok().put("memberReceiveAddress", memberReceiveAddress);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress){
		memberReceiveAddressService.save(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody MemberReceiveAddressEntity memberReceiveAddress){
		memberReceiveAddressService.updateById(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberReceiveAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
