    package com.example.blog_backend.Services;

    import java.time.LocalDate;
    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.example.blog_backend.Exceptions.ResourceNotFoundException;
    import com.example.blog_backend.Models.CategoryModel;
    import com.example.blog_backend.Models.PostModel;
    import com.example.blog_backend.Models.UserModel;
    import com.example.blog_backend.Repo.CategoryRepo;
    import com.example.blog_backend.Repo.PostRepo;
    import com.example.blog_backend.Repo.UserRepo;

    @Service 
    public class PostService 
    {
        @Autowired 
        private PostRepo postRepo;

        @Autowired
        private UserRepo userRepo;

        @Autowired 
        private CategoryRepo categoryRepo;

        //add post----
        public PostModel addPost(PostModel post)
        {
            if (post == null)        //// Check karo ki request mein Post object mila hai ya nahi
            {
                throw new IllegalArgumentException("Post data is required");
            }

            if(post.getUser()==null || post.getUser().getId()==null)
            {
                throw new IllegalArgumentException("User Id is required");
            }

            if(post.getCategory()==null || post.getCategory().getCId()==null)
            {
                throw new IllegalArgumentException("Category Id is required");
            }

            Long categoryId=post.getCategory().getCId();  //category id niklana
            Long userId=post.getUser().getId();    //user id nikali


            UserModel user=userRepo.findById(userId).    //userid se user search kaarna or na ho to exeption
                orElseThrow(() -> 
                    new ResourceNotFoundException
                        ("User not found with Id :" + userId)
                    );
            
                CategoryModel category = categoryRepo.findById(categoryId)
                .orElseThrow(() ->
                    new ResourceNotFoundException
                        ("Category not found with Id :" + categoryId)
                    );

            post.setUser(user);   //post ke sath user ko connect karna 
            post.setCategory(category);   //post ke sath category connet

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

            //request body empty null toh nhi 
            if(updatePost==null)    
            {
                throw new IllegalArgumentException("Post Data is Required");
            }


            //check category object or id  
            if(updatePost.getCategory()==null ||
                updatePost.getCategory().getCId()==null)
            {
                throw new IllegalArgumentException("Category id is required");
            }


            //post ko database me find karenge
            PostModel post= postRepo.findById(id)             
            .orElseThrow(() -> new ResourceNotFoundException
                ("Post not found with id "+ id)
            );

            //ctaegory id lena request se 
            Long categoryId= updatePost.getCategory().getCId();

            //category find karna databse 
            CategoryModel category=categoryRepo.findById(categoryId)
                .orElseThrow(() ->
                    new ResourceNotFoundException
                    ("Category not found with id :"+ categoryId)
                );
            
            //update post details
            post.setTitle(updatePost.getTitle());
            post.setPost(updatePost.getPost());
            post.setCategory(category);

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
