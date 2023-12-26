package com.example.blogsystemlab12.Controller;

import com.example.blogsystemlab12.Model.Blog;
import com.example.blogsystemlab12.Model.User;
import com.example.blogsystemlab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bs/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // user blogs
    @GetMapping("/get")
    public ResponseEntity getBlogs(@AuthenticationPrincipal User auth){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogs(auth.getId()));
    }
    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity blogById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.blogById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity blogByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.blogByTitle(title));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog , @AuthenticationPrincipal  User auth){
        blogService.addBlog(blog,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Blog added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@PathVariable Integer id, @RequestBody @Valid Blog blog, @AuthenticationPrincipal User auth){
        blogService.updateBlog(id,blog,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Blog updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal User auth){
        blogService.deleteBlog(id,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Blog deleted");
    }
}
