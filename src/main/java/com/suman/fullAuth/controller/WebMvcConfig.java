package com.suman.fullAuth.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
            @Override
            public void addViewControllers(@NonNull ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("redirect:/home");
                registry.addViewController("/home").setViewName("index");
                registry.addViewController("/terms").setViewName("terms");
                registry.addViewController("/privacy").setViewName("privacy");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/about").setViewName("about");
                registry.addViewController("/dashboard").setViewName("dashboard");
                registry.addViewController("/register").setViewName("register");
                registry.addViewController("/reset").setViewName("reset");
                registry.addViewController("/forget").setViewName("forget");
                registry.addStatusController("/error", HttpStatusCode.valueOf(404));
            }
}
