package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.CommentModel;

public interface CommentRepo extends JpaRepository <CommentModel, Long>
{
   
}
