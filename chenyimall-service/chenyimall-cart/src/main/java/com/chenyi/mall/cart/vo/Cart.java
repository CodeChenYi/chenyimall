package com.chenyi.mall.cart.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyi
 * @className Cart
 * @date 2022/6/29 20:09
 */
public class Cart {

    /**
     * 购物项
     */
    private List<CartItem> cartItemList;

    /**
     * 商品总数量
     */
    private int totalCount;

    /**
     * sku数量，一共有几种商品
     */
    private int skuCount;

    /**
     * 全部商品总价
     */
    private BigDecimal totalPrice = new BigDecimal("0.00");

    /**
     * 商品优惠价格
     */
    private BigDecimal reduce = new BigDecimal("0.00");

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public int getTotalCount() {
        int count = 0;
        if (cartItemList != null && cartItemList.size() > 0) {
            for (CartItem cartItem : cartItemList) {
                count += cartItem.getCount();
            }
        }
        totalCount = count;
        return totalCount;
    }

    public void setSkuCount(int skuCount) {
        this.skuCount = skuCount;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getSkuCount() {
        return cartItemList != null ? cartItemList.size() : 0;
    }

    public BigDecimal getTotalPrice() {
        if (cartItemList != null && cartItemList.size() > 0) {
            for (CartItem cartItem : cartItemList) {
                // 计算选中商品价格
                if (cartItem.isCheck()) {
                    totalPrice = totalPrice.add(cartItem.getTotalPrice());
                }
            }
        }
        totalPrice = totalPrice.subtract(reduce);
        return totalPrice;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItemList=" + cartItemList +
                ", totalCount=" + totalCount +
                ", skuCount=" + skuCount +
                ", totalPrice=" + totalPrice +
                ", reduce=" + reduce +
                '}';
    }
}
