package com.security.jwt.jwt.security;

import com.security.jwt.jwt.models.JwtUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Date;

@Component
public class AccessTokenService implements Serializable {

    static final String JWT_SECRET = "secret";
    static final String TOKEN_HEADER = "Authentication";
    static final long TOKEN_PERIOD = 300000;
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
    public String validateToken(String token){
        Claims body = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
       // return (body.get("id").equals(Integer.toString(user.getId()))) && !isTokenExpire(body.getExpiration());
        return body.getSubject();
    }
}
