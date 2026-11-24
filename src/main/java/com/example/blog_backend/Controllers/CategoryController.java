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

import com.example.blog_backend.Models.CategoryModel;
import com.example.blog_backend.Services.CategoryService;

import jakarta.validation.Valid;

@RestController 
@CrossOrigin("*")
@RequestMapping("/api/categories")

public class CategoryController 
{
    @Autowired 
    private CategoryService categoryService;


    //add category-----
    @PostMapping
    public CategoryModel addCategory(@Valid @RequestBody CategoryModel category)
    {
        return categoryService.addCategory(category);
    }

    //get all category-----
    @GetMapping 
    public List<CategoryModel> getAllCategory()
    {
        return categoryService.getAllCategory();
    }

    //get by id------
    @GetMapping("/{id}")
    public CategoryModel getCategoryById(@PathVariable Long id)
    {
        return categoryService.getCategoryById(id);
    }

    //update category------
    @PutMapping("/{id}")
    public CategoryModel updateCategory(@Valid @RequestBody CategoryModel updateCategory, @PathVariable Long id)
    {
        return categoryService.updateCategory(id, updateCategory);
    }

    //delete category-----
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
        return categoryService.deleteCategory(id);
    }
    
}
