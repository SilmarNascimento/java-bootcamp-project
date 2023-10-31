package com.silmarfnascimento.bootcampproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SpringSecurityConfig {

  @Autowired
  private JWTFilter jwtFilter;

  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc)
      throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> {
              auth
                  .requestMatchers(mvc.pattern(HttpMethod.POST, "/users/")).permitAll()
                  .requestMatchers(mvc.pattern(HttpMethod.POST, "/auth/login")).permitAll()
                  .anyRequest().authenticated();
            }
        )
        .addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
    return http.build();
  }
}
