package com.springstudy.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.vo.User;


@Controller  
public class Login {  
	
   private static Logger logger = Logger.getLogger(Login.class);  
   // private ApplicationContext ac = null;  
   @Resource  
   private IUserService userService ;  
   @RequestMapping("/login")  
    public String login(String username,String password,Model model){  
        if (username.equals(password))   
        {  
            User user = userService.getUserById(1);  
            System.out.println(user.getUserName());  
            logger.info("Öµlogin£º"+user.getUserName());  
            logger.info(JSON.toJSONString(user));  
            model.addAttribute("username", user.getUserName());  
            return "Ok";  
        } else {  
            return "No";  
        }  
    }  

}  