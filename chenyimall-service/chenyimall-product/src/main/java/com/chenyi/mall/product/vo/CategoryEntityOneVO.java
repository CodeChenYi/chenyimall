package com.chenyi.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenyi
 * @className CategoryEntityOneVO
 * @date 2022/9/22 13:05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryEntityOneVO {

    private String name;

    private String catId;

    private List<CategoryEntityTwoVO> CategoryEntityTwoVO;

}
