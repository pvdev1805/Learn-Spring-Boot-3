package com.learnspring.hello_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloSpringController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello, Spring!";
    }
}
