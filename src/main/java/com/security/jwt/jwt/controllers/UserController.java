package com.security.jwt.jwt.controllers;

import com.security.jwt.jwt.security.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AccessTokenService accessTokenService;

    @GetMapping("hello")
    public String hello(){
        return "Hello JWT Security Application";
    }

    @GetMapping("token")
    public String tokenize(@RequestParam String username){
        return "Access Token : <br>"+accessTokenService.generateToken(username);
    }

}
