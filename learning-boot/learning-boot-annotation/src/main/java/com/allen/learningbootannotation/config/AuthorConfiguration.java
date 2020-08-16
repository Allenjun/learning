package com.allen.learningbootannotation.config;

import com.allen.demospringbootstarter.Author;
import com.allen.demospringbootstarter.AuthorProperties;
import com.allen.demospringbootstarter.DefaultAuthor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JUN
 * @Description TODO
 * @createTime 20:51
 */
@Configuration
public class AuthorConfiguration {
    
    @Bean
    public Author author(AuthorProperties authorProperties) {
        DetailAuthor detailAuthor = new DetailAuthor();
        detailAuthor.setAuthorProperties(authorProperties);
        return detailAuthor;
    }
    
    class DetailAuthor extends DefaultAuthor {
        
        @Override
        public void introduce() {
            System.out.println("Hello, 我的名字是" + getAuthorProperties().getName());
        }
    }
    
}
