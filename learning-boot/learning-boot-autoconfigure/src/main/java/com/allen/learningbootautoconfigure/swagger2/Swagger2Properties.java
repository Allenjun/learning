package com.allen.learningbootautoconfigure.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 翟永超 Create date ：2017/8/7. My blog： http://blog.didispace.com
 */
@Data
@ConfigurationProperties("api.swagger2")
class Swagger2Properties {

    /**
     * 是否开启swagger
     */
    private Boolean enabled;

    /**
     * 标题
     */
    private String title = "";
    /**
     * 描述
     */
    private String description = "";
    /**
     * 版本
     */
    private String version = "";

    private String basePackage = "";
}
