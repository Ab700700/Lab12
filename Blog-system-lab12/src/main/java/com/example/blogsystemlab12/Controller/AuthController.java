package com.example.blogsystemlab12.Controller;

import com.example.blogsystemlab12.Model.User;
import com.example.blogsystemlab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bs/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(HttpStatus.OK).body("Logged");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(HttpStatus.OK).body("Logged out");
    }


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUsers());
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid User oldUser,@AuthenticationPrincipal User user){
        authService.updateUser(oldUser.getId(),user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable  Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }
}
