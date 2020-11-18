package com.allen.learningbootsecurity.config;

import com.allen.learningbootsecurity.jwt.JWTAuthenticationFilter;
import com.allen.learningbootsecurity.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author JUN @Description TODO
 * @createTime 23:51
 */
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableRedisHttpSession
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    MyCsrfRequestMatcher myCsrfRequestMatcher;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    MyCorsConfigurationSource myCorsConfigurationSource;
    @Autowired
    MySessionInformationExpiredStrategy mySessionInformationExpiredStrategy;
    @Autowired
    MyUserDetailService userDetailService;
    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @description: 配置AuthenticationManager（认证管理器）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* 基于内存的认证方式 */
        //        configureInMemory(auth);

        /* 基于数据库的认证方式 */
        configureDataSource(auth);

        // 基于第三方的认证中心的方式
//        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*整合JWT*/
        //        configureJWT(http);

        /*完全配置*/
        configureCommon(http);
    }

    private void configureDataSource(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    private void configureInMemory(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // 实际注册了InMemoryUserDetailsManager
                .passwordEncoder(bCryptPasswordEncoder())
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN", "NORMAL")
                .and()
                .withUser("allen")
                .password(bCryptPasswordEncoder().encode("allen"))
                .authorities("ROLE_NORMAL")
                .and();
    }

    private void configureJWT(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin().successHandler(myAuthenticationSuccessHandler);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void configureCommon(HttpSecurity http) throws Exception {
        /*http.authorizeRequests()
                .antMatchers("/asdfg").hasRole("ADMIN")
                .antMatchers("/fghj").hasAuthority("ROLE_ADMIN")
                .antMatchers("/sdasd").access("hasIpAddress('192.168.1.0/24') and hasRole('ADMIN')");
*/

        /* 会话Session管理 */
        http.sessionManagement()
                .maximumSessions(1) // 账号同时在线登录数
                .maxSessionsPreventsLogin(false) // 新登录的用户把旧登录踢掉
                //            .expiredSessionStrategy(mySessionInformationExpiredStrategy)
                .expiredUrl("/login"); // 被踢掉用户定向网址

        /* 认证、授权失败管理 */
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler); // 处理授权不通过请求(AccessDeniedException)
    /*
    处理未认证请求(AuthenticationException)
    注意：使用自定义的authenticationEntryPoint后，DefaultLoginPageGeneratingFilter、DefaultLogoutPageGeneratingFilter
    将不会被注册到过滤器链中，也就是需要从authenticationEntryPoint中重定向到登录页面
    */
        //            .authenticationEntryPoint(myAuthenticationEntryPoint);

        /* remember me */
        http.rememberMe().tokenValiditySeconds(3600);

        /* 跨站请求伪造 */
        http.csrf()
                .csrfTokenRepository(
                        new LazyCsrfTokenRepository(
                                new HttpSessionCsrfTokenRepository())) // token仓库，负责生成、获取、保存token
                .requireCsrfProtectionMatcher(myCsrfRequestMatcher); // 配置哪些请求需要csrf验证

        /* 匿名用户 */
        http.anonymous().authorities("ROLE_NORMAL");

        /* 跨域请求 */
        http.cors().configurationSource(myCorsConfigurationSource);

        /* 登出 */
        http.logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true);

        /* 强制使用Https */
        //        http.requiresChannel().antMatchers("/requieHttps").requiresSecure();
        /* 端口映射 配合requiresChannel使用*/
        //        http.portMapper().http(8888).mapsTo(443);

        /* 授权权限配置，可以和方法注解混合使用 */
        http.authorizeRequests()
                .antMatchers("/dynamicPerm")
                .access("@rbacService.hasPermission(request, authentication)");

        /* Basic登录 (注册：BasicAuthenticationFilter) */
        http.httpBasic().authenticationEntryPoint(myAuthenticationEntryPoint); // 配置认证失败时处理逻辑

        /* 表单形式登录(注册：UsernamePasswordAuthenticationFilter) */
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", false)
                .loginProcessingUrl("/login") // UsernamePasswordAuthenticationFilter拦截Post请求
                //            .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                //            .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .passwordParameter("username")
                .usernameParameter("password")
                .withObjectPostProcessor(
                        new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {

                            @Override
                            public <O extends UsernamePasswordAuthenticationFilter> O postProcess(
                                    O object) {
                                object.setRequiresAuthenticationRequestMatcher(
                                        new AntPathRequestMatcher("/login", "POST"));
                                return object;
                            }
                        }); // withObjectPostProcessor方法可以修改filter的成员属性

        // 第三方登录
        http.oauth2Login();
    }
}
