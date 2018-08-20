package com.security.jwt.jwt.security;

import com.security.jwt.jwt.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class AccessTokenService implements Serializable {

    @Value("${app.secret}")
    private String JWT_SECRET;

    static final String TOKEN_HEADER = "Authentication";
    static final long TOKEN_PERIOD = 30000;
    private Clock clock = DefaultClock.INSTANCE;

    public String generateToken(String username, int id){

        //Calculate token time data
        Date createdDate = clock.now();
        Date expireDate = calculateExpireDate(createdDate);

        //Create token claims
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id",Integer.toString(id));

        //Generating token
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
        return token;
    }

    //Calculate token expire data on given token period
    private Date calculateExpireDate(Date createdDate){
        return new Date(createdDate.getTime()+TOKEN_PERIOD);
    }

    //Check token expiration
    public Boolean isTokenExpire(Date expireDate){
        return expireDate.before(clock.now());
    }

    //Validate the token
    public String validateUser(String token){
        try {
            Claims body = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
            return "Authorized user : "+body.getSubject();
        }catch (ExpiredJwtException e){
            return "This token expired";
        }catch (SignatureException e){
            return "Unauthorized user";
        }
    }

}
