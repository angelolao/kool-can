package com.example.cool_kan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Kool-Can!";
    }

    @GetMapping("/login")
    public String login() {
        return "This is the login page. Click <a href='/oauth2/authorization/google'>here</a> to login with Google.";
    }
}