package com.chenyi.gulimall.cart.to;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenyi
 * @className CartItemTO
 * @date 2022/6/30 23:27
 */
public class CartItemTO {

    /**
     * skuId
     */
    private String skuId;

    /**
     * 是否被选中
     */
    private boolean check = false;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku默认图片
     */
    private String defaultImage;

    /**
     * sku价格
     */
    private BigDecimal price;

    /**
     * sku数量
     */
    private int count;

    /**
     * sku总价
     */
    private BigDecimal totalPrice;

    /**
     * sku是否还有库存
     */
    private int skuStock;

    /**
     * sku是否下架
     */
    private boolean skuExpired = false;

    /**
     * 销售属性信息
     */
    private List<String> attr;

    public int getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(int skuStock) {
        this.skuStock = skuStock;
    }

    public boolean isSkuExpired() {
        return skuExpired;
    }

    public void setSkuExpired(boolean skuIsExpired) {
        this.skuExpired = skuIsExpired;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal(String.valueOf(this.count)));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getAttr() {
        return attr;
    }

    public void setAttr(List<String> attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "skuId='" + skuId + '\'' +
                ", check=" + check +
                ", skuName='" + skuName + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", attr=" + attr +
                '}';
    }
}
