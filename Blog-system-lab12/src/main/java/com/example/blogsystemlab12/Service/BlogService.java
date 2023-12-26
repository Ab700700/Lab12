package com.example.blogsystemlab12.Service;

import com.example.blogsystemlab12.Api.ApiException;
import com.example.blogsystemlab12.Model.Blog;
import com.example.blogsystemlab12.Model.User;
import com.example.blogsystemlab12.Repository.AuthRepository;
import com.example.blogsystemlab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getBlogs(Integer auth){
        User user = authRepository.findUserById(auth);
        if(user == null) throw new ApiException("User not found");
        List<Blog> blogs = blogRepository.findAllByUser(user);
        return blogs;
    }

    public List<Blog> getAll(){
        return blogRepository.findAll();
    }
    public void addBlog(Blog blog, Integer auth){
        User user = authRepository.findUserById(auth);
        if(user == null) throw  new ApiException("User not found");
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer id, Blog blog, Integer auth){
        Blog oldBlog = blogRepository.findBlogById(id);
        if(oldBlog == null) throw new ApiException("Blog not found");
        User user = authRepository.findUserById(auth);
        if(user == null) throw new ApiException("Something went wrong");
        blog.setId(id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void deleteBlog(Integer id , Integer auth){
        Blog blog = blogRepository.findBlogById(id);
        if (blog == null) throw new ApiException("Blog not found");
        User user = authRepository.findUserById(auth);
        if(user == null)throw new ApiException("User not found");
        if(!blog.getUser().equals(user))throw new ApiException("Something went wrong");
        blogRepository.delete(blog);

        }
    public Blog blogById(Integer id){
        Blog blog = blogRepository.findBlogById(id);
        if(blog == null) throw new ApiException("Blog not found");
        return blog;
    }

    public Blog blogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if(blog == null) throw new ApiException("Blog not found");
        return blog;
    }
    }


