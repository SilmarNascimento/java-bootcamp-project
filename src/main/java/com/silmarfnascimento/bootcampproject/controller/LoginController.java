package com.silmarfnascimento.bootcampproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.silmarfnascimento.bootcampproject.dto.Login;
import com.silmarfnascimento.bootcampproject.service.Implementation.LoginService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/auth")
@Tag(name = "LoginController")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @Operation(summary = "Realiza o login do usuário", method = "POST")
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid Login login) {
    ServiceResponse serviceResponse = loginService.login(login);
    if (serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
          .body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body("Cliente não encontrado");
  }
}
