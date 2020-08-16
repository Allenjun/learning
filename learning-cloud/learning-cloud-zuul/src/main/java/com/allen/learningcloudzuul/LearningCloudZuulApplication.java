package com.allen.learningcloudzuul;

import com.spring4all.swagger.EnableSwagger2Doc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@EnableZuulProxy
@SpringBootApplication
public class LearningCloudZuulApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LearningCloudZuulApplication.class, args);
    }
    
    @Primary
    @EnableSwagger2Doc
    @Configuration
    class DocumentationConfig implements SwaggerResourcesProvider {
        
        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            resources.add(swaggerResource("learning-cloud-client", "/learning-cloud-client/v2/api-docs", "2.0"));
            resources.add(swaggerResource("learning-cloud-service", "/learning-cloud-service/v2/api-docs", "2.0"));
            return resources;
        }
        
        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
    
    /*
    @Bean
    ZuulFilter zuulFilter() {
        return new AccessFilter();
    }
    */
}
