package com.security.jwt.jwt.services;

import com.security.jwt.jwt.repositories.UserRepo;
import com.security.jwt.jwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String raw_password = authentication.getCredentials().toString();
        String password = bCryptPasswordEncoder.encode(raw_password);
        User u =  userRepo.findByName(name);
        if(u==null){
            System.out.println("Username not found!");
            throw new BadCredentialsException("Username not found!");
        }
        else if(!bCryptPasswordEncoder.matches(raw_password,u.getPassword())){
            System.out.println("Invalid credentials!");
            throw new BadCredentialsException("Invalid credentials!");
        }
        return new UsernamePasswordAuthenticationToken(name,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
