package com.security.jwt.jwt.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class AccessTokenService {

    static final String JWT_SECRET = "secret";
    static final String TOKEN_HEADER = "Authentication";
    static final long TOKEN_PERIOD = 300000;

    public String generateToken(String username){
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_PERIOD))
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
        return jwt;
    }

}
