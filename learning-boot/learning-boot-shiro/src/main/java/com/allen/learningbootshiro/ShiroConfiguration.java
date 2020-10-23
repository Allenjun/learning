package com.allen.learningbootshiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chan
 * @date 2020/10/7
 * description: TODO
 */
@Configuration
public class ShiroConfiguration {

    @Value("${session.redis.expireTime}")
    private int expireTime;

    @Bean
    public SecurityManager securityManager(AuthorizingRealm userAuthorizingRealm, SessionManager defaultWebSessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userAuthorizingRealm);
        securityManager.setSessionManager(defaultWebSessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录页面，无权限时跳转的路径
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 配置拦截规则
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SessionManager defaultWebSessionManager(SessionDAO redisSessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(expireTime * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(new SimpleCookie("JSESSIONID"));
        return sessionManager;
    }

    @Bean
    public SessionDAO redisSessionDao() {
        return new RedisSessionDao();
    }

    @Bean
    public AuthorizingRealm userAuthorizingRealm() {
        return new UserAuthorizingRealm();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
