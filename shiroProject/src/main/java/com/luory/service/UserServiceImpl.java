package com.luory.service;

import com.luory.bean.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luory
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public List<Role> getRoleByAccount(String account) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("管理员");
        role.setMark("admin");
        roles.add(role);
        return roles;
    }
}
