package com.example.blog_backend.Exceptions;

public class ResourceNotFoundException extends RuntimeException 
{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
