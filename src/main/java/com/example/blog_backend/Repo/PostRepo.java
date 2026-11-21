package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.PostModel;

public interface PostRepo extends JpaRepository <PostModel, Long>
{
   
}