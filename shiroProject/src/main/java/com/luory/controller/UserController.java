package com.luory.controller;

import com.luory.bean.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/testAuthentication")
    public User testAuthentication(){
        User user = new User();
        user.setAccount("account");
        return user;
    }

    @PostMapping("/testAuthorizate")
    @RequiresRoles("admin")
    public Map<String, Object> testAuthorizate(){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("statusCode",200);
        return dataMap;
    }
}
