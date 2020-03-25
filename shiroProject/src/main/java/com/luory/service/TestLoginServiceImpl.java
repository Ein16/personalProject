package com.luory.service;

import com.luory.bean.User;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
/**
 * @author luory
 */
@Service
public class TestLoginServiceImpl implements LoginService{
    @Override
    public Object login(@NotNull Object principal, @NotNull Object credentials) {
        String account = (String) principal;
        String password = (String) credentials;
        User user = new User();
        user.setPassword(password);
        user.setAccount(account);
        return user;
    }
}
