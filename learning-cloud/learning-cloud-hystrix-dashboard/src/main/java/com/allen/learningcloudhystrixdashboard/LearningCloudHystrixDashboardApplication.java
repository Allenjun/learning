package com.allen.learningcloudhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class LearningCloudHystrixDashboardApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LearningCloudHystrixDashboardApplication.class, args);
    }
    
}
