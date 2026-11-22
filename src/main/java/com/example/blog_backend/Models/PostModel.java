package com.example.blog_backend.Models;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor 
@Getter 
@Setter 
public class PostModel 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long postId;

    @NotBlank(message= "Title is Required")
    private String title;
    
    @NotBlank(message = "Post content is Required")
    private String post;
    
    private LocalDate postDate;

    @ManyToOne 
    private UserModel user;

    @OneToMany(mappedBy ="post")
    @JsonIgnore 
    private List<CommentModel> comments;
    
}
