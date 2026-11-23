package com.example.blog_backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_backend.Models.PostModel;
import com.example.blog_backend.Services.PostService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts")

public class PostController 
{

    @Autowired
    private PostService postService;

    //add post
    @PostMapping 
    public PostModel addPost(@Valid @RequestBody PostModel post)
    {
        return postService.addPost(post);
    }

    //get all post
    @GetMapping 
    public List<PostModel> getAllPost()
    {
        return postService.getAllPost();
    }

    //get post by id
    @GetMapping("/{id}")
    public PostModel getPostById(@PathVariable Long id)
    {
        return postService.getPostById(id);
    }

    //update post---
    @PutMapping("/{id}")
    public PostModel updatePost(@Valid @RequestBody PostModel updatepost,@PathVariable Long id)
    {
        return postService.updatePost(updatepost, id);
    }

    //delete Post---
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id)
    {
        return postService.deletePost(id);
    }

    
    
}
