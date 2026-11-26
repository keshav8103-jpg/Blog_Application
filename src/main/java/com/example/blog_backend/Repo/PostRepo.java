package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.PostModel;

public interface PostRepo extends JpaRepository <PostModel, Long>
{
   // Check karata hai ess ID se post hai kya
    boolean existsByUser_Id(Long userId);
}