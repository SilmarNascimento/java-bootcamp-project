package com.silmarfnascimento.bootcampproject.security;

import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var servletPath = request.getServletPath();
    if (servletPath.startsWith("/users") && request.getMethod().equals("POST")) {
      filterChain.doFilter(request, response);
    } else if(servletPath.startsWith("/auth/login") && request.getMethod().equals("POST")) {
      filterChain.doFilter(request, response);
    } else {
      String token =  request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
      try {
        if(token!=null && !token.isEmpty()) {
          JWTObject tokenUserObject = JWTCreator.create(token,SecurityConfiguration.PREFIX, SecurityConfiguration.KEY);
          Optional<User> userFound = userRepository.findByUsername(tokenUserObject.getUsername());

          if(userFound.isEmpty()) {
            response.sendError(404, "usuário não encontrado");
            return;
          }

          if(userFound.get().getPassword().equals(tokenUserObject.getPassword())) {
            request.setAttribute("idUser", userFound.get().getId());
            filterChain.doFilter(request, response);
            return;
          }

          response.sendError(403, "usuário não autorizado");
          return;
        }else {
          response.sendError(403, "Usuário não encontrado");
          return;
        }
        // filterChain.doFilter(request, response);
      }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
        System.out.println("Error processing JWT: " + e.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
      }
    }
  }
}
