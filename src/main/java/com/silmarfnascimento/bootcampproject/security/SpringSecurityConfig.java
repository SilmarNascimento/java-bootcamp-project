package com.silmarfnascimento.bootcampproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

  @Autowired
  private JWTFilter jwtFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> {
              auth
                  .requestMatchers(HttpMethod.POST, "/users/").permitAll()
                  .requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
              auth.anyRequest().authenticated();
            }
        ).addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
    return http.build();
  }
}
