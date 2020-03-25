package com.luory.service;

import com.luory.bean.Role;

import java.util.List;

/**
 * 用户服务
 * @author luory
 */
public interface UserService {
    /**
     * 根据用户账号获取角色
     * @param account
     * @return
     */
    public List<Role> getRoleByAccount(String account);
}
