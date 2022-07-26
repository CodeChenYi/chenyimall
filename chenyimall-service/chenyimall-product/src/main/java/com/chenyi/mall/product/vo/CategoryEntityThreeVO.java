package com.chenyi.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryEntityThreeVO {
    private String name;

    private String catId;

    private String parentId;
}