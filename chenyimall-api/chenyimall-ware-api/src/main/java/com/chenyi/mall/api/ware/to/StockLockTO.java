package com.chenyi.mall.api.ware.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenyi
 * @className StockLockTO
 * @date 2022/7/31 17:31
 */
@Data
public class StockLockTO implements Serializable {

    private Long taskId;

    private WareDetailTO detail;

}
