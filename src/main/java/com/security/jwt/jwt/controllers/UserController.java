package com.security.jwt.jwt.controllers;

import com.security.jwt.jwt.models.JwtUser;
import com.security.jwt.jwt.security.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    JwtUser jwtUser;

    @GetMapping("hello")
    public String hello(){
        return "Hello JWT Security Application";
    }

    @GetMapping("token")
    public String tokenize(@RequestParam String username,@RequestParam int id){
        return "Access Token : <br>"+accessTokenService.generateToken(username,id);
    }

    @GetMapping("validate")
    public String validate(@RequestParam String token){
        return "Token user name is " + accessTokenService.validateToken(token);
    }

    @GetMapping("test")
    public String test(){
        return "This is the test";
    }

}
