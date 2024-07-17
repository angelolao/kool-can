package com.example.cool_kan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Cool Kan!";
    }

    @GetMapping("/test")
    public String testCors() {
        return "CORS is working!";
    }
}