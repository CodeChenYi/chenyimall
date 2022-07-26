package com.chenyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * spu信息介绍
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private String spuId;
	/**
	 * 商品介绍
	 */
	private String decript;

}
