package com.example.blog_backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.CommentModel;
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
        
    }

    //get all comment--------
    public List<CommentModel> getAllComment()
    {
        return commentRepo.findAll();
    }

    //get by id -------
    public CommentModel getCommentById(Long id)
    {
        return commentRepo.findById(id).orElse(null);
    }
    
    //update comment-------
    public CommentModel updateComment(CommentModel updateComment, Long id)
    {
        CommentModel comment=commentRepo.findById(id).orElse(null);

        if(comment!=null)
        {
            comment.setComment(updateComment.getComment());

            return commentRepo.save(comment);
        }
        return null;
    }

    //delete comment---------
    public String deleteComment(Long id)
    {
        CommentModel comment=commentRepo.findById(id).orElse(null);

        if(comment!=null)
        {
            commentRepo.deleteById(id);
            return "Comment deleted Successfully";
        }   
        return "Comment not Found";
    }

}
