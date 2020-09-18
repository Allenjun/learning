package com.allen.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DefaultConfiguration {

    @Bean
    @Primary
    public IRule rule() {
        return new WeightedResponseTimeRule();
    }
}
