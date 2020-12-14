package com.allen.springbootsecurityoauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JUN @Description TODO
 * @createTime 23:51
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // 实际注册了InMemoryUserDetailsManager
                .passwordEncoder(bCryptPasswordEncoder())
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN", "NORMAL")
                .and()
                .withUser("allen")
                .password(bCryptPasswordEncoder().encode("1234"))
                .authorities("ROLE_NORMAL");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }



    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
