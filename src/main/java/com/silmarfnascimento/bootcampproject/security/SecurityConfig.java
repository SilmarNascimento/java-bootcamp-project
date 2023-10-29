package com.silmarfnascimento.bootcampproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
              auth
                  .requestMatchers(HttpMethod.POST, "/users/").permitAll()
                  .requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
              auth.anyRequest().authenticated();
            }
        );
    return http.build();
  }
}
