package com.chenyi.gulimall.cart.service;

import com.chenyi.gulimall.cart.vo.Cart;
import com.chenyi.gulimall.cart.vo.CartItem;

import java.util.List;

/**
 * @author chenyi
 * @className CartService
 * @date 2022/6/29 20:51
 */
public interface CartService {
    /**
     * 添加购物车选项
     *
     * @param skuId
     * @param count
     * @return
     */
    CartItem addCartItem(String skuId, Integer count);

    /**
     * 修改商品数量
     *
     * @param skuId
     * @param isAdd 是否添加
     */
    void updateCartItemCount(String skuId, boolean isAdd);

    /**
     * 获取购物车列表
     * @return
     */
    Cart getCartList();

    /**
     * 删除购物项
     * @param skuId
     */
    void deleteCartItem(String[] skuId);

    /**
     * 修改选中状态
     *
     * @param skuId
     * @param isCheck
     */
    void checkCartItem(String skuId, boolean isCheck);

    /**
     * 获取购物车选中商品
     * @return
     */
    List<CartItem> getCheckCartItem();

}
