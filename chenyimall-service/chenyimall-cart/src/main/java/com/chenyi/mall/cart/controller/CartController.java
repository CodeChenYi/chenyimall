package com.chenyi.mall.cart.controller;

import com.chenyi.mall.cart.service.CartService;
import com.chenyi.mall.cart.vo.Cart;
import com.chenyi.mall.cart.vo.CartItem;
import com.chenyi.mall.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenyi
 * @className CartController
 * @date 2022/6/29 20:45
 */
@RequestMapping("/cart")
@RestController
public class CartController {

    @Resource
    CartService cartService;

    /**
     * 添加到购物车
     * @param skuId
     * @param count
     * @return
     */
    @PostMapping("/addCartItem")
    public R addCartItem(@RequestParam String skuId, @RequestParam Integer count) {
        CartItem cartItem = cartService.addCartItem(skuId, count);
        return R.ok().put("cartItem", cartItem);
    }

    /**
     * 修改购物车单个商品数量
     * @param skuId
     * @param isAdd
     * @return
     */
    @PutMapping("/updateCartItemCount")
    public R updateCartItemCount(@RequestParam String skuId, @RequestParam boolean isAdd) {
        cartService.updateCartItemCount(skuId, isAdd);
        return R.ok();
    }

    /**
     * 获取购物车列表
     * @return
     */
    @GetMapping("/getCartList")
    public R getCartList() {
        Cart cart = cartService.getCartList();
        return R.ok().put("cartList", cart);
    }

    /**
     * 删除购物车商品
     * @param skuId
     * @return
     */
    @DeleteMapping("/deleteCartItem")
    public R deleteCartItem(@RequestParam String[] skuId) {
        cartService.deleteCartItem(skuId);
        return R.ok();
    }

    /**
     * 修改购物车选中状态
     * @param skuId
     * @param isCheck
     * @return
     */
    @PutMapping("/checkCartItem")
    public R checkCartItem(@RequestParam String skuId, @RequestParam boolean isCheck) {
        cartService.checkCartItem(skuId, isCheck);
        return R.ok();
    }

    /**
     * 获取购物车选中状态商品
     * @return
     */
    @GetMapping("/getCheckCartItem")
    public R getCheckCartItem() {
        List<CartItem> cartItemList = cartService.getCheckCartItem();
        return R.ok().put("cartItemList", cartItemList);
    }
}
