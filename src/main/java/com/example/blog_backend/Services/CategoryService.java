    package com.example.blog_backend.Services;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.CategoryModel;
    import com.example.blog_backend.Repo.CategoryRepo;

    @Service 
    public class CategoryService 
    {
        @Autowired 
        private CategoryRepo categoryRepo;

        //add category---
        public CategoryModel addCategory(CategoryModel category)
        {
            if(category==null)
            {
                throw new IllegalArgumentException("Category data is required");
            }

            return categoryRepo.save(category);
        }

        //get all category---
        public List<CategoryModel> getAllCategory()
        {
            return categoryRepo.findAll();
        }

        //get category by id---------
        public CategoryModel getCategoryById(Long id)
        {
            return categoryRepo.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException
                    ("Category not found with id :" +id)
                );
        }

        //update category----------
        public CategoryModel updateCategory(Long id, CategoryModel updateCategory)
        {

            if(updateCategory ==null)
            {
                throw new IllegalArgumentException("Category data is required");
            }

            CategoryModel category=categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                    ("Category not found with id :"+ id)
                );


            category.setTitle(updateCategory.getTitle());

            return categoryRepo.save(category);
        }

        //delete category--------
        public String deleteCategory(Long id)
        {
            CategoryModel category=categoryRepo.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException
                ("Category not found with id: "+ id)
            );

            categoryRepo.delete(category);
            return "Category deleted successfully";
        }

    }
