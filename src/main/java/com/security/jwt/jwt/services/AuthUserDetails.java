package com.security.jwt.jwt.services;

import com.security.jwt.jwt.models.Login;
import com.security.jwt.jwt.repositories.UserRepo;
import com.security.jwt.jwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetails implements UserDetailsService {

    UserRepo userRepo;

    @Autowired
    public AuthUserDetails(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public AuthUserDetails() {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //System.out.println("User details service :"+s);

        //System.out.println("Repo Test : "+userRepo);
        User u = userRepo.findByName(s);

        if(u!=null) {
            Login login = new Login(u.getName(), u.getPassword());
            return login;
        }else {
            throw  new UsernameNotFoundException("Invalid credentials");
        }
    }
}
