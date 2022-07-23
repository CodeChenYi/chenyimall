package com.chenyi.gulimall.order.vo;

import com.chenyi.gulimall.cart.to.CartItemTO;
import com.chenyi.gulimall.member.to.MemberReceiveAddressTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单确认页数据
 * @author chenyi
 * @className OrderConfirmVO
 * @date 2022/6/30 23:24
 */
public class OrderConfirmVO {

    /**
     * 选中购物车数据
     */
    private List<CartItemTO> cartItemList;

    /**
     * 会员地址
     */
    private List<MemberReceiveAddressTO> memberReceiveAddressList;

    /**
     * 可使用的优惠券id
     */
    private List<String> availableCouponIdList;

    /**
     * 所有优惠券id
     */
    private List<String> allCouponIdList;

    /**
     * 优惠总价格
     */
    private BigDecimal totalCouponPrice;

    /**
     * 订单总额
     */
    private BigDecimal totalPrice = new BigDecimal("0.00");

    /**
     * 应付价格
     */
    private BigDecimal payPrice = new BigDecimal("0.00");

    public List<String> getAvailableCouponIdList() {
        return availableCouponIdList;
    }

    public void setAvailableCouponIdList(List<String> availableCouponIdList) {
        this.availableCouponIdList = availableCouponIdList;
    }

    public List<String> getAllCouponIdList() {
        return allCouponIdList;
    }

    public void setAllCouponIdList(List<String> allCouponIdList) {
        this.allCouponIdList = allCouponIdList;
    }

    public List<MemberReceiveAddressTO> getMemberReceiveAddressList() {
        return memberReceiveAddressList;
    }

    public void setMemberReceiveAddressList(List<MemberReceiveAddressTO> memberReceiveAddressList) {
        this.memberReceiveAddressList = memberReceiveAddressList;
    }

    public List<CartItemTO> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemTO> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public BigDecimal getTotalPrice() {
        cartItemList.forEach(cartItem -> {
            totalPrice = totalPrice.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        });
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPayPrice() {
        cartItemList.forEach(cartItem -> {
            payPrice = payPrice.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        });
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }
}
