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

import com.example.blog_backend.Models.CommentModel;
import com.example.blog_backend.Services.CommentService;

@RestController 
@CrossOrigin("*")
@RequestMapping("/api/comments")

public class CommentController 
{
    @Autowired
    private CommentService commentService;

    
    //add comment------
    @PostMapping
    public CommentModel addComment(@RequestBody CommentModel comment)
    {
        return commentService.addComment(comment);
    }

    //get all comment-----
    @GetMapping 
    public List<CommentModel> getAllComment()
    {
        return commentService.getAllComment();
    }

    //get comment by id-----
    @GetMapping("/{id}")
    public CommentModel getCommentById(@PathVariable Long id)
    {
        return commentService.getCommentById(id);
    }

    //update comment----
    @PutMapping("/{id}")
    public CommentModel updateComment(@RequestBody CommentModel comment, @PathVariable Long id)
    {
        return commentService.updateComment(comment, id);
    }
    
    //delete comment---
    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id)
    {
        return commentService.deleteComment(id);
    }
}
