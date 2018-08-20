package com.security.jwt.jwt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavigationsController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("test")
    public String home(){
        return "test";
    }

}
