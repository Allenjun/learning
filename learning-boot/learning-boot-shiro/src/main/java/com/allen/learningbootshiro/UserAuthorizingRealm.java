package com.allen.learningbootshiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author chan
 * @date 2020/10/7
 * description: TODO
 */
public class UserAuthorizingRealm extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) principals.getPrimaryPrincipal();
        System.out.println("授权认证：" + principal);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>(Arrays.asList(new String[]{"admin", "system"})));
        authorizationInfo.setStringPermissions(new HashSet<String>(Arrays.asList(new String[]{"goods:add", "goods:del"})));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        return new SimpleAuthenticationInfo("程序猿", usernamePasswordToken.getPassword(), getName());
    }
}
