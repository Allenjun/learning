package com.allen.demospringbootstarter;

/**
 * @author JUN
 * @Description TODO
 * @createTime 17:21
 */
public class DefaultAuthor implements Author {
    
    private AuthorProperties authorProperties;
    
    public AuthorProperties getAuthorProperties() {
        return authorProperties;
    }
    
    public void setAuthorProperties(AuthorProperties authorProperties) {
        this.authorProperties = authorProperties;
    }
    
    @Override
    public void introduce() {
        System.out.println("Hi, 我是" + authorProperties.getName());
    }
}
