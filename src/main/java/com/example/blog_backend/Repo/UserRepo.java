package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.UserModel;

public interface UserRepo extends JpaRepository <UserModel, Long> 
{
   boolean existsByEmail(String email);
}
