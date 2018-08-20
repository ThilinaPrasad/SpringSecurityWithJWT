package com.security.jwt.jwt.services;

import com.security.jwt.jwt.models.User;
import com.security.jwt.jwt.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User applicationUser = userRepo.findByName(s);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(s);
        }
        return (UserDetails) new User(applicationUser.getName(), applicationUser.getPassword());
    }
    }

