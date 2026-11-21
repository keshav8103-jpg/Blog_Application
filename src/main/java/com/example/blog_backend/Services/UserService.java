package com.example.blog_backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.UserModel;
import com.example.blog_backend.Repo.UserRepo;

@Service 
public class UserService 
{

    @Autowired 
    private UserRepo userRepo;

    //add user-----
    public UserModel addUser(UserModel user)
    {
        return userRepo.save(user);   
    }

    //get user-----
    public List<UserModel> getAllUser()
    {
        return userRepo.findAll();
    }

    //get user by id-----
    public UserModel getUserById(Long id)
    {
        return userRepo.findById(id).orElseThrow
        (() -> new ResourceNotFoundException("User Not Found With id : "+ id));
    }


    //update user----
    public UserModel updateUser(Long id, UserModel updateUser)
    {
        UserModel user=userRepo.findById(id).orElseThrow
        (() -> new ResourceNotFoundException("User Not Found With id :"+ id));

        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());

        return userRepo.save(user);
    }

    //delete user------
    public String deleteUser(Long id)
    {
        UserModel user=userRepo.findById(id).orElseThrow
        (() -> new ResourceNotFoundException("User Not Found With id :"+ id));
        
        userRepo.delete(user);
        return "User deleted Successfully";
    }
}
