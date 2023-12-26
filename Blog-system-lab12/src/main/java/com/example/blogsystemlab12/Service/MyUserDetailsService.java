package com.example.blogsystemlab12.Service;

import com.example.blogsystemlab12.Api.ApiException;
import com.example.blogsystemlab12.Model.User;
import com.example.blogsystemlab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  user = authRepository.findUserByUsername(username);
        if(user == null) throw  new ApiException("Password or username went wrong");
        return user;
    }
}
