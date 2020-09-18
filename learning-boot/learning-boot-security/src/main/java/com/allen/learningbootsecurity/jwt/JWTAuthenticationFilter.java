package com.allen.learningbootsecurity.jwt;

import com.allen.learningbootsecurity.jwt.JWTTokenUtil.Payload;
import com.allen.learningbootsecurity.security.MyUserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author JUN @Description JWT认证过滤器
 * @createTime 15:34
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailService myUserDetailService;
    private AntPathRequestMatcher matcher = new AntPathRequestMatcher("/login");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return matcher.matches(request);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jws = StringUtils.substringAfter(request.getHeader("Authorization"), "Bearer ");
        if (StringUtils.isNoneBlank(jws)) {
            Payload payload = JWTTokenUtil.parseToken(jws);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (payload.getExpire().after(new Date())
                    && (authentication == null || authentication.getName() == null)) {
                UserDetails userDetails = myUserDetailService.loadUserByUsername(payload.getSub());
                if (userDetails != null) {
                    authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails.getUsername(), null, userDetails.getAuthorities());
                    ((UsernamePasswordAuthenticationToken) authentication).setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
