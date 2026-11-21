package com.example.blog_backend.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.PostModel;
import com.example.blog_backend.Models.UserModel;
import com.example.blog_backend.Repo.PostRepo;
import com.example.blog_backend.Repo.UserRepo;

@Service 
public class PostService 
{
    @Autowired 
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    //add post----
    public PostModel addPost(PostModel post)
    {

        Long userId=post.getUser().getId();    //user id nikali

        UserModel user=userRepo.findById(userId).    //userid se user search kaarna or na ho to exeption
            orElseThrow(() -> 
                new ResourceNotFoundException
                    ("User not found with Id :" + userId)
                );
        
        post.setUser(user);   //post ke sath user ko connect karna 

        post.setPostDate(LocalDate.now());   //post ki current date set karna

        return postRepo.save(post);    //post ko database me save karna

    }

    //get all post-----
    public List<PostModel> getAllPost()
    {
        return postRepo.findAll();
    }


    //get post by id-----
    public PostModel getPostById(Long id)
    {
        return postRepo.findById(id).   //id se post search karee nhi toh exception throw 404
            orElseThrow(() ->
                new ResourceNotFoundException
                ("Post not found with id :"+ id)
            );
    }


    //update post-----
    public PostModel updatePost(PostModel updatePost, Long id)
    {
       PostModel post= postRepo.findById(id)              //post ko database me find karenge
        .orElseThrow(() -> new ResourceNotFoundException
            ("Post not found with id "+ id)
        );

        post.setTitle(updatePost.getTitle());
        post.setPost(updatePost.getPost());
        post.setPostDate(updatePost.getPostDate());

        return postRepo.save(post);
    }

    //delete post------
    public String deletePost(Long id)
    {
        PostModel post=postRepo.findById(id)   //post find karegenge
            .orElseThrow(() -> 
                new ResourceNotFoundException("Post not found with id :"+id)
            );

        postRepo.delete(post);

        return "Post deleted Successfully";
    }
}
