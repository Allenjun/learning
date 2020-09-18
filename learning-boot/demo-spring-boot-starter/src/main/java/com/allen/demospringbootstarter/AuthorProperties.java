package com.allen.demospringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JUN @Description TODO
 * @createTime 17:25
 */
@ConfigurationProperties(prefix = "author")
public class AuthorProperties {

    private boolean enabled;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
