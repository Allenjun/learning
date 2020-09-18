package com.allen.demospringbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JUN @Description TODO
 * @createTime 17:13
 */
@Configuration
@ConditionalOnClass(Author.class)
@ConditionalOnProperty(prefix = "author", value = "enabled", havingValue = "true")
@EnableConfigurationProperties(AuthorProperties.class)
public class AuthorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Author.class)
    public Author author(AuthorProperties authorProperties) {
        DefaultAuthor defaultAuthor = new DefaultAuthor();
        defaultAuthor.setAuthorProperties(authorProperties);
        return defaultAuthor;
    }
}
