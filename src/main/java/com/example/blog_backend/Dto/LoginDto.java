package com.example.blog_backend.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 

public class LoginDto 
{
    private String email;

    private String password;
    
}
