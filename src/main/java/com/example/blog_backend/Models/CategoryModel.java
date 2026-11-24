package com.example.blog_backend.Models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Setter 
@Getter 
@NoArgsConstructor

public class CategoryModel 
{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cId;

    @NotBlank(message = "Category title is required")
    @Size (max = 100, message = "Category title cannot exceed 100 characters")
    private String title;

    @OneToMany(mappedBy = "category")
    @JsonIgnore 
    private List<PostModel> post;
    
}
