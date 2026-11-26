package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.UserModel;

public interface UserRepo extends JpaRepository <UserModel, Long> 
{
   //database me email check krta hai
   boolean existsByEmail(String email);

   //email se user find karta hai database me 
   UserModel findByEmail(String email);
}
