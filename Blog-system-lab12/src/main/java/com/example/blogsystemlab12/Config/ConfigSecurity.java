package com.example.blogsystemlab12.Config;

import com.example.blogsystemlab12.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
       http.csrf().disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
               .and()
               .authenticationProvider(authenticationProvider())
               .authorizeHttpRequests()
               .requestMatchers("/api/v1/bs/blog/get-all","/api/v1/bs/user/register").permitAll()
               .anyRequest().authenticated()
               .and()
               .logout().logoutUrl("/api/v1/bs/user/logout")
               .deleteCookies("JSESSIONID")
               .invalidateHttpSession(true)
               .and()
               .httpBasic();
       return http.build();
    }

}
