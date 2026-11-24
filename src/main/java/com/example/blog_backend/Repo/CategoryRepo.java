package com.example.blog_backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_backend.Models.CategoryModel;

public interface CategoryRepo extends JpaRepository <CategoryModel, Long>
{

    
}