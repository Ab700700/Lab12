package com.example.blogsystemlab12.Repository;

import com.example.blogsystemlab12.Model.Blog;
import com.example.blogsystemlab12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);

    Blog findBlogByTitle(String title);

    List<Blog> findAllByUser(User user);
}
