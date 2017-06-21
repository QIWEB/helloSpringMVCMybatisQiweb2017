package com.springstudy.controller;

 

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

 

@Controller

public class HelloController {

 

         @RequestMapping("/hello5")

         public ModelAndView hello9(){

                   ModelAndView mv =new ModelAndView();

                   mv.addObject("spring", "spring mvc");

                   mv.setViewName("hello");

                   return mv;

         }
         
         @RequestMapping(value="login.do")
         public ModelAndView getBPDataByDay(HttpServletRequest request,HttpServletResponse response){
         Map model = new HashMap();
         String showData  = request.getParameter("showData");
         model.put("showData", showData);
         return new ModelAndView("login",model);
         }

}