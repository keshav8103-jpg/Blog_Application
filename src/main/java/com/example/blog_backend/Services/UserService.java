package com.example.blog_backend.Services;

import com.example.blog_backend.Repo.CommentRepo;
import com.example.blog_backend.Repo.PostRepo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog_backend.Dto.LoginDto;
import com.example.blog_backend.Exceptions.DuplicateEmailException;
import com.example.blog_backend.Exceptions.ResourceNotFoundException;
import com.example.blog_backend.Models.UserModel;
import com.example.blog_backend.Repo.UserRepo;

@Service 
public class UserService 
{

    @Autowired 
    private UserRepo userRepo;

    @Autowired 
    private PostRepo postRepo;

    @Autowired 
    private CommentRepo commentRepo;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired 
    private JwtService jwtService;

    
    //Login user--------
    public String loginUser(LoginDto loginDto)
    {
        UserModel user=userRepo.findByEmail(loginDto.getEmail());

        if(user==null)
        {
            throw new ResourceNotFoundException("User not found");
        }

        //user password ko database me hassh password se match
        boolean isPasswordMatch=passwordEncoder.matches
        (
            loginDto.getPassword(),user.getPassword()
        );

        // Agar password match nahi hua toh error denge.
        if(!isPasswordMatch)
        {
            throw new IllegalArgumentException("Invalid password");
        }

        // Email aur password sahi hone par success JWT token generate karenge.
        return jwtService.generateToken(user.getEmail());


    }

    //add user-----
    public UserModel addUser(UserModel user)
    {

        // Check kar rahe hain ki user data null toh nahi hai.
        if(user==null)
        {
            throw new IllegalArgumentException("User data is required");
        }

         // Check kar rahe hain ki email pehle se database mein exist toh nahi karti.
        if(userRepo.existsByEmail(user.getEmail()))
        {
            throw new DuplicateEmailException("Email already exists: "+ user.getEmail());
        }   

        // Original password ko BCrypt hash mein convert kar rahe hain.
        String encodedPassword=passwordEncoder.encode(user.getPassword());

        // User object mein original password ki jagah hashed password set kar rahe hain.
        user.setPassword(encodedPassword);

        //save krta hai
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

        if(updateUser==null)
        {
            throw new IllegalArgumentException("User data is required");
        }
        UserModel user=userRepo.findById(id).orElseThrow
        (() -> new ResourceNotFoundException("User Not Found With id :"+ id));

        if(!user.getEmail().equals(updateUser.getEmail())
            && userRepo.existsByEmail(updateUser.getEmail()))
        {
            throw new DuplicateEmailException(
                "Email already exists: "+ updateUser.getEmail()
            
            );
        }
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());

        //password ko hash karke set karta hai
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        return userRepo.save(user);
    }

    //delete user------
    public String deleteUser(Long id)
    {
        //user check kare hai 
        UserModel user=userRepo.findById(id).orElseThrow
        (() -> new ResourceNotFoundException("User Not Found With id :"+ id));
        

        //check karo user ki post hai 
        if(postRepo.existsByUser_Id(id))
        {
            throw new IllegalArgumentException
            ("cannot delete user because post are associat with this user");
        }

        //check karo ki user ki comment hai
        if(commentRepo.existsByUser_Id(id))
        {
            throw new IllegalArgumentException
            ("Cannot delete user because comments are associated with this user");
    
        }

        //delet user jab post or comment na ho
        userRepo.delete(user);

        return "User deleted Successfully";
    }
}
