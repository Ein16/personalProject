package com.luory.config;

import com.luory.bean.Role;
import com.luory.bean.User;
import com.luory.service.LoginService;
import com.luory.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luory
 * 自定义校验器
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles = userService.getRoleByAccount(account);
        authorizationInfo.addRoles(roles.stream().map(role -> {
            return role.getMark();
        }).collect(Collectors.toList()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String account = (String) authenticationToken.getPrincipal();
        String password = String.valueOf((char[]) authenticationToken.getCredentials());
        User user = (User) loginService.login(account,password);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getAccount(),user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}
