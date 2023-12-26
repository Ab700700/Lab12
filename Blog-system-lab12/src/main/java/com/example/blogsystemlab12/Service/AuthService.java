package com.example.blogsystemlab12.Service;

import com.example.blogsystemlab12.Api.ApiException;
import com.example.blogsystemlab12.Model.Blog;
import com.example.blogsystemlab12.Model.User;
import com.example.blogsystemlab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void register(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public List<User> getUsers(){
        return authRepository.findAll();
    }

    public void updateUser(Integer id, User user){
        User oldUser = authRepository.findUserById(id);
        if(oldUser == null) throw new ApiException("User not found");
        user.setId(id);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);
        if(user == null) throw  new ApiException("User not found");
        authRepository.delete(user);
    }


}
