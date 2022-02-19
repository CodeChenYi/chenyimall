package com.chenyi.gulimall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryEntityTwoVO {

    private String name;

    private String catId;

    private String parentId;

    private List<CategoryEntityThreeVO> categoryEntityThreeVOList;

}
