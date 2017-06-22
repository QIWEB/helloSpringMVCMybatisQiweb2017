package com.spring.task;

import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
  
  
public class MyTaskTest {  
    public static void main(String[] args) {  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/configs/spring-task.xml");  
    }  
}  