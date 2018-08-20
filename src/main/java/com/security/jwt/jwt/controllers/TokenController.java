package com.security.jwt.jwt.controllers;

import com.security.jwt.jwt.models.User;
import com.security.jwt.jwt.repositories.UserRepo;
import com.security.jwt.jwt.security.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenController {

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/token")
    public String token(@RequestParam String name){
        User user = userRepo.findByName(name);
        if(user!=null) {
            return "Access token : " + accessTokenService.generateToken(user.getName(), user.getId());
        }else{
            return "cannot find user";
        }
    }

    @GetMapping("/validate")
    public String tokenValidate(@RequestParam String token){
        return accessTokenService.validateUser(token);
    }
}
