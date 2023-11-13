package com.silmarfnascimento.bootcampproject.controller;

import com.silmarfnascimento.bootcampproject.dto.UserRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.service.Implementation.UserService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/users")
@Tag(name = "UserController")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<Object> findAllUsers() {
    ServiceResponse serviceResponse = userService.findAll();
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findUserById(UUID id) {
    ServiceResponse serviceResponse = userService.findById(id);
    if (serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
          .body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body("Cliente não encontrado");
  }

  @Operation(summary = "Realiza o registro do usuário", method = "POST")
  @PostMapping
  public ResponseEntity<Object> registerUser(@RequestBody @Valid UserRegister userRegister) {
    User user = new User(
        userRegister.name(),
        userRegister.username(),
        userRegister.email(),
        userRegister.password(),
        userRegister.image());
    ServiceResponse serviceResponse = userService.create(user);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody User user) {
    ServiceResponse serviceResponse = userService.update(id, user);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    userService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
