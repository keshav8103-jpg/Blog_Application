package com.example.blog_backend.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor 
public class CommentModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank(message="Comment is Required")
    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    private String comment;

    @ManyToOne
    private UserModel user;        //many comment one user

    @ManyToOne 
    private PostModel post;        //many commnet one post
    
}
