package com.allen.learningbootannotation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author JUN @Description TODO
 * @createTime 16:39
 */
@Configuration
@ImportResource(locations = "classpath:bean.xml")
public class ImportXmlConfiguration {

}
