package com.luory.controller;

import com.luory.bean.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luory
 * 登录控制器
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if(WebUtil.isAjax(httpServletRequest)){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("message","should login");
            WebUtil.responseOutWithJson(httpServletResponse,dataMap);
        }else{
            //redirect loginPage;
        }
    }

    @PostMapping("/passwordLogin")
    @ResponseBody
    public String passwordLogin(@RequestBody LoginVo loginVo){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                loginVo.getAccount(),
                loginVo.getPassword()
        );
        subject.login(usernamePasswordToken);
        return "success";
    }

    @PostMapping("/logout")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();;
    }
}
