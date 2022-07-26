package com.chenyi.mall.common.annotation;

import com.chenyi.mall.common.enums.OperateType;

import java.lang.annotation.*;

/**
 * @className LogAnnotation
 * @author chenyi
 * @date 2022/7/26 17:19
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**
     * 方法操作名称
     */
    String title() default "";

    /**
     * 模块名称
     * @return
     */
    String module();

    /**
     * 执行的操作
     * @return
     */
    OperateType operateType() default OperateType.OTHER;
}
