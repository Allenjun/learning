package com.allen.learningbootannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author JUN
 * @Description TODO
 * @createTime 17:04
 */
@Configuration
@ComponentScan(basePackages = { "com.allen.otherpackage" })
public class ScanOtherPackageConfiguration {
    
}
