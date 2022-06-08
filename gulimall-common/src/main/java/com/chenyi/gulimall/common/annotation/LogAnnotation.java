package com.chenyi.gulimall.common.annotation;

import com.chenyi.gulimall.common.enums.OperateType;

import java.lang.annotation.*;

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
