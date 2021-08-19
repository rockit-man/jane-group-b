package com.example.helpingout.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index"); //main landing pg
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/userhome").setViewName("userhome"); //user landing pg
        registry.addViewController("/login").setViewName("login"); //login
    }

}