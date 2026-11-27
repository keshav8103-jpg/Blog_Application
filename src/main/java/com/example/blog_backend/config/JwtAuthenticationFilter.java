
package com.example.blog_backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.blog_backend.Repo.UserRepo;
import com.example.blog_backend.Services.JwtService;

import java.util.Collections;

import com.example.blog_backend.Models.UserModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    
    @Autowired 
    private JwtService jwtService;
    
    @Autowired 
    private UserRepo userRepo;

    @Override 
    protected void doFilterInternal
    (
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain  
    ) 
    throws ServletException, IOException
    {

        String authHeader= request.getHeader("Authorization");

        //check karna ki authorization header null hai Bearer se start nahi ho raha
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        //bearer ke bad actual jwt token nikalna
        String token =authHeader.substring(7);

        //jwt token se useer ka email nikalna
        String email=jwtService.extractEmail(token);
        

        // email se database me user find
        UserModel user=userRepo.findByEmail(email);

        if(user != null)
        {
            //User ki authentication details banana
            UsernamePasswordAuthenticationToken 
            authentication = new UsernamePasswordAuthenticationToken
            (
                email,
                null,
                Collections.emptyList()
            );

            // current request ke liye authenticaation mark karta hai
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }
}
