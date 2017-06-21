package com.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	 @RequestMapping("/hello6")

     public ModelAndView hello(){

               ModelAndView mv =new ModelAndView();

               mv.addObject("spring", "spring mvc");

               mv.setViewName("hello6");

               return mv;

     }
}
