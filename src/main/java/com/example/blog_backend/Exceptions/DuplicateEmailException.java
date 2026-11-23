package com.example.blog_backend.Exceptions;


public class DuplicateEmailException extends RuntimeException 
{
    public DuplicateEmailException(String message)
    {
        super(message);
    }

}
