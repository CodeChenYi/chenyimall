package com.chenyi.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chenyi
 * @className LockWareVO
 * @date 2022/7/30 15:34
 */
@Data
public class LockWareVO {
    private Long skuId;

    private Integer num;

    private List<Long> wareIds;

    private String skuName;
}
