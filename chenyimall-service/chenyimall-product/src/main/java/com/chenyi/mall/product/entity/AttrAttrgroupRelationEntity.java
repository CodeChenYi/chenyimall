package com.chenyi.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 属性&属性分组关联
 * 
 * @author chenyi
 * @email codezixuan@outlook.com
 * @date 2021-10-04 22:53:33
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private String id;
	/**
	 * 属性id
	 */
	private String attrId;
	/**
	 * 属性分组id
	 */
	private String attrGroupId;
	/**
	 * 属性组内排序
	 */
	private Integer attrSort;

}
