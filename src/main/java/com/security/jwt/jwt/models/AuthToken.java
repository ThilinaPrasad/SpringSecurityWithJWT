package com.security.jwt.jwt.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public AuthToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
