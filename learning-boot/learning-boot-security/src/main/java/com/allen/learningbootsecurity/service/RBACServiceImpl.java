package com.allen.learningbootsecurity.service;

import com.allen.learningbootsecurity.mapper.SysMenuMapper;
import com.allen.learningbootsecurity.mapper.SysRoleMapper;
import com.allen.learningbootsecurity.mapper.SysUserMapper;
import com.allen.learningbootsecurity.pojo.BO.UserBO;
import com.allen.learningbootsecurity.pojo.DO.SysRole;
import com.allen.learningbootsecurity.pojo.DO.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author JUN @Description TODO
 * @createTime 14:36
 */
@Service("rbacService")
public class RBACServiceImpl implements RBACService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    private AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public UserDetails loadUserByUsername(String name) {
        Optional<SysUser> optionalSysUser = sysUserMapper.findByUserName(name);
        if (optionalSysUser.isPresent()) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            SysUser sysUser = optionalSysUser.get();
            List<SysRole> sysRoles = sysRoleMapper.selectByUserId(sysUser.getUserId());
            sysRoles.forEach(
                    sysRole -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRole.getRoleKey()));
                    });
            return UserBO.builder()
                    .username(name)
                    .password(sysUser.getPassword())
                    .authorities(authorities)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
        }
        return null;
    }

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //        return authentication.getAuthorities().stream()
        //            .anyMatch(authoritie -> matcher.matchStart(authoritie.getAuthority(),
        // request.getRequestURI()));r
        return false;
    }
}
