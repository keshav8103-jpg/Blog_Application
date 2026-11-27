package com.example.blog_backend.Services;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;


@Service 
public class JwtService 
{

    //application properties se jwt secret key read karna 
    @Value("${jwt.secret}")
    private String secretKey;


    // secret key ko jwt signin key me convert karana 
    private SecretKey getSigningKey()
    {
    //secret key ko byte array me convert
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);

    //byte array se singing key bnana
    return Keys.hmacShaKeyFor(keyBytes);
    }

    
    //user ke email se jwt token generate karna
    public String generateToken(String email)
    {
        return Jwts.builder()
        .subject(email)           //token me user ka email set karna 
        .issuedAt(new Date())     //token kab generate hua
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  //token 1 ghte ke bad expire hoga
        .signWith(getSigningKey())   //secret signing key se token ko sign karna
        .compact();                  //final jwt token bnana
    }


    //jwt token se user ka email nikalna
    public String extractEmail(String token)
    {
        return Jwts.parser()
            .verifyWith(getSigningKey())   //secret key se token verify karan

            .build()                        

            .parseSignedClaims(token)      //token ko read oor verify karna

            .getPayload()

            .getSubject();                //email return karna
    }

}
