package com.chenyi.mall.cart.service.impl;

import com.chenyi.mall.cart.service.CartService;
import com.chenyi.mall.cart.interceptor.CartInterceptor;
import com.chenyi.mall.cart.vo.Cart;
import com.chenyi.mall.cart.vo.CartItem;
import com.chenyi.mall.common.constant.ChenYiMallConstant;
import com.chenyi.mall.common.enums.ResultEnum;
import com.chenyi.mall.common.utils.R;
import com.chenyi.mall.api.member.to.MemberInfo;
import com.chenyi.mall.api.product.feign.ProductFeignService;
import com.chenyi.mall.api.product.to.SkuInfoTO;
import com.chenyi.mall.api.ware.feign.WareFeignService;
import com.chenyi.mall.api.ware.to.WareSkuTo;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author chenyi
 * @className CartServiceImpl
 * @date 2022/6/29 20:51
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private WareFeignService wareFeignService;

    @Resource
    private ThreadPoolExecutor executor;

    @Override
    public CartItem addCartItem(String skuId, Integer count) {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        log.info("cartRedisHash {}", cartRedisHash);
        // 先查看当前购物车是否已经存在当前商品
        CartItem jsonCartItem = (CartItem) cartRedisHash.get(skuId);
        if (jsonCartItem != null) {
            jsonCartItem.setCount(jsonCartItem.getCount() + count);
            cartRedisHash.put(skuId, jsonCartItem);
            return jsonCartItem;
        } else {
            CartItem cartItem = new CartItem();
            // 异步方式查询数据并封装
            CompletableFuture<Void> skuInfoFuture = CompletableFuture.runAsync(() -> {
                R info = productFeignService.info(skuId);
                if (ResultEnum.SUCCESS.getCode().equals(info.getCode())) {
                    SkuInfoTO skuInfo = info.get("skuInfo", SkuInfoTO.class);
                    cartItem.setSkuId(skuId);
                    cartItem.setCount(count);
                    cartItem.setCheck(true);
                    cartItem.setPrice(skuInfo.getPrice());
                    cartItem.setSkuName(skuInfo.getSkuName());
                    cartItem.setDefaultImage(skuInfo.getSkuDefaultImg());
                }
            }, executor);

            CompletableFuture<Void> saleAttrFuture = CompletableFuture.runAsync(() -> {
                List<String> saleAttr = productFeignService.getSaleAttrBySkuId(skuId);
                cartItem.setAttr(saleAttr);
            }, executor);

            CompletableFuture.allOf(skuInfoFuture, saleAttrFuture).join();
            cartRedisHash.put(skuId, cartItem);
            return cartItem;
        }
    }

    @Override
    public void updateCartItemCount(String skuId, boolean isAdd) {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        CartItem cartItem = (CartItem) cartRedisHash.get(skuId);
        if (cartItem != null) {
            if (isAdd) {
                cartItem.setCount(cartItem.getCount() + 1);
                cartRedisHash.put(skuId, cartItem);
            } else {
                if (cartItem.getCount() > 1) {
                    cartItem.setCount(cartItem.getCount() - 1);
                    cartRedisHash.put(skuId, cartItem);
                }
            }
        }
    }

    @Override
    public Cart getCartList() {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        // 获取所有商品
        List<Object> values = cartRedisHash.values();
        Cart cart = new Cart();
        if (values != null && values.size() > 0) {
            List<CartItem> cartItems = values.stream().map(obj -> (CartItem) obj).collect(Collectors.toList());
            cart.setCartItemList(cartItems);
        }
        return cart;
    }

    @Override
    public void deleteCartItem(String[] skuId) {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        cartRedisHash.delete(skuId);
    }

    @Override
    public void checkCartItem(String skuId, boolean isCheck) {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        CartItem cartItem = (CartItem) cartRedisHash.get(skuId);
        assert cartItem != null;
        if (isCheck) {
            cartItem.setCheck(false);
            cartRedisHash.put(skuId, cartItem);
        } else {
            cartItem.setCheck(true);
            cartRedisHash.put(skuId, cartItem);
        }
    }

    @Override
    public List<CartItem> getCheckCartItem() {
        BoundHashOperations<String, Object, Object> cartRedisHash = getCartRedisHash();
        log.info("cartRedisHash {}", cartRedisHash);
        List<Object> values = cartRedisHash.values();
        log.info("values{}", values);
        log.info("--------------------查询选中商品-----------------------");
        if (values != null && values.size() > 0) {
            log.info("-------------开始过滤-------------");
            // 过滤掉未选中的商品项
            List<CartItem> cartItems = values.stream()
                    .map(item -> (CartItem) item)
                    .filter(CartItem::isCheck).collect(Collectors.toList());
            // 获取选中的全部skuId
            List<String> cartItemSkuIds =
                    cartItems.stream().map(CartItem::getSkuId).collect(Collectors.toList());

            // 查询sku商品是否还有库存
            CompletableFuture<Void> wareSkuFuture = CompletableFuture.runAsync(() -> {
                R r = wareFeignService.infoBySkuId(cartItemSkuIds);
                if (ResultEnum.SUCCESS.getCode().equals(r.getCode())) {
                    List<WareSkuTo> wareSkuList = r.get("wareSkuList", new TypeReference<List<WareSkuTo>>() {
                    });
                    for (CartItem cartItem : cartItems) {
                        AtomicInteger count = new AtomicInteger();
                        wareSkuList.stream()
                                .filter(wareSkuTo -> cartItem.getSkuId().equals(wareSkuTo.getSkuId()))
                                .forEach(wareSkuTo -> {
                                    count.addAndGet(wareSkuTo.getStock() - wareSkuTo.getStockLocked());
                                });
                        cartItem.setSkuStock(count.get());
                    }
                }
            }, executor);

            // 远程查询当前sku价格
            CompletableFuture<Void> skuPriceFuture = CompletableFuture.runAsync(() -> {
                R skuPriceById = productFeignService.getSkuPriceById(cartItemSkuIds);
                if (ResultEnum.SUCCESS.getCode().equals(skuPriceById.getCode())) {
                    // 重新设置sku价格
                    List<SkuInfoTO> skuPrice = skuPriceById.get("skuPriceList", new TypeReference<List<SkuInfoTO>>() {
                    });
                    for (CartItem cartItem : cartItems) {
                        for (SkuInfoTO skuInfoTO : skuPrice) {
                            if (skuInfoTO.getSkuId().equals(cartItem.getSkuId())) {
                                cartItem.setPrice(skuInfoTO.getPrice());
                            }
                        }
                    }
                }
            }, executor);

            CompletableFuture.allOf(wareSkuFuture, skuPriceFuture).join();

            return cartItems;

        }
        return null;
    }

    private BoundHashOperations<String, Object, Object> getCartRedisHash() {
        // 获取用户信息
        MemberInfo memberInfo = CartInterceptor.threadLocal.get();
        assert memberInfo != null;
        Long id = memberInfo.getMember().getId();
        return redisTemplate.boundHashOps(ChenYiMallConstant.CART_USER + id);
    }
}
