package com.example.helpingout.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Add each HTML page in here for view
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index"); //main landing pg
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login"); //login
        registry.addViewController("/user-home").setViewName("user-home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration").setViewName("registration");

    }

}