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
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * Class JWTFilter - Authenticate a user based on the provided token.
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

  @Autowired
  private IUserRepository userRepository;

  /**
   * Method doFilterInternal - Implements a filter to authenticate a user based on token
   * validation.
   *
   * @param request     - request http object.
   * @param response    - responde http object.
   * @param filterChain - object provided by the servlet  ralated to a chain of a filtered request
   *                    for a resource.
   * @throws ServletException - Exception thrown by servlet error.
   * @throws IOException      - Exception thrown while accessing information using streams, files
   *                          and directories.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    SecurityContextHolder.getContext().setAuthentication(null);
    String token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
    if (token == null || token.isEmpty()) {
      response.sendError(403, "Token inválido");
      return;
    }
    try {
      JWTObject tokenUserObject = JWTCreator.create(token, SecurityConfiguration.PREFIX,
          SecurityConfiguration.KEY);
      Optional<User> userFound = userRepository.findByUsername(tokenUserObject.getUsername());

      if (userFound.isEmpty()) {
        response.sendError(404, "usuário não encontrado");
        return;
      }

      if (userFound.get().getPassword().equals(tokenUserObject.getPassword())) {
        request.setAttribute("idUser", userFound.get().getId());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userFound.get().getId(), null,
            Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
        return;
      }

      response.sendError(403, "usuário não autorizado");

    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException |
             SignatureException e) {
      System.out.println("Error processing JWT: " + e.getMessage());
      response.setStatus(HttpStatus.FORBIDDEN.value());
    }
  }
}
