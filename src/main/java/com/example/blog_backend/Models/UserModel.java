package com.example.blog_backend.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@NoArgsConstructor 
@Getter 
@Setter 
public class UserModel 
{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message="UserName is Required")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Please enter Valid Email")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min=6, message="Password must be at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @OneToMany(mappedBy = "user")
    @JsonIgnore 
    private List<PostModel> posts;

    @OneToMany (mappedBy = "user")
    @JsonIgnore 
    private List<CommentModel> comments;

    
}
