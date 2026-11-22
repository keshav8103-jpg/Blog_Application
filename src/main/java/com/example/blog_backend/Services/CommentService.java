package com.example.blog_backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.CommentModel;
import com.example.blog_backend.Models.PostModel;
import com.example.blog_backend.Models.UserModel;
import com.example.blog_backend.Repo.CommentRepo;
import com.example.blog_backend.Repo.PostRepo;
import com.example.blog_backend.Repo.UserRepo;

@Service 
public class CommentService 
{
    @Autowired 
    private CommentRepo commentRepo;

    @Autowired 
    private UserRepo userRepo;

    @Autowired 
    private PostRepo postRepo;

    //add comment---------
    public CommentModel addComment(CommentModel comment)
    {
        Long userId=comment.getUser().getId();   //user id find karna

        UserModel user=userRepo.findById(userId).  //user check in database
            orElseThrow
            (() -> new ResourceNotFoundException
            ("User Not Found With id :"+ userId));


        Long postId =comment.getPost().getPostId();  //post id find karna 
        
        PostModel post=postRepo.findById(postId).    //post check karna database me 
            orElseThrow(() -> new ResourceNotFoundException
                ("Post Not Found with Id :"+ postId));

        comment.setUser(user);   //user ko comment ke sath set karna 

        comment.setPost(post);   //post ko comment ke sath set karna

        return commentRepo.save(comment);

    }

    //get all comment--------
    public List<CommentModel> getAllComment()
    {
        return commentRepo.findAll();
    }

    //get by id -------
    public CommentModel getCommentById(Long id)
    {
        return commentRepo.findById(id).                 //comment nhi mila to exeption throw
        orElseThrow(() -> new ResourceNotFoundException
        ("Comment not found with id :"+ id));
    }
    
    //update comment-------
    public CommentModel updateComment(CommentModel updateComment, Long id)
    {
        CommentModel comment=commentRepo.findById(id).
        orElseThrow(() -> new ResourceNotFoundException
        ("Comment not found with id :"+ id));

        comment.setComment(updateComment.getComment());

        return commentRepo.save(comment);
    }

    //delete comment---------
    public String deleteComment(Long id)
    {
        CommentModel comment=commentRepo.findById(id).
        orElseThrow(() -> new ResourceNotFoundException
        ("Comment not found with id :"+ id));

            commentRepo.deleteById(id);
            return "Comment deleted Successfully";
    }

}
