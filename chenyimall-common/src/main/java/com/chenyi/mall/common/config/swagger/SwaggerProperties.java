package com.chenyi.mall.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 映射配置文件Swagger基本属性
 * @author TAO
 * @date 2021/4/25 13:18
 */
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled = false;

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";

    /**
     * 接口路径
     */
    private String ipAddress = "";
}

