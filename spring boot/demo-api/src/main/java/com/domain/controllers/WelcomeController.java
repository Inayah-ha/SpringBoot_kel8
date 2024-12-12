package com.domain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/main")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Selamat datang di SpringBoot REST API";
    }
}