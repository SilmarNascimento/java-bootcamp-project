package com.silmarfnascimento.bootcampproject.controller;


import com.silmarfnascimento.bootcampproject.model.Comment;
import com.silmarfnascimento.bootcampproject.service.ICommentService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/blogpost/{blogpostId}/comment")
public class CommentController {
  @Autowired
  private ICommentService commentService;

  @GetMapping
  public ResponseEntity<Object> findAllComments() {
    ServiceResponse serviceResponse = commentService.findAll();
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findCommentById(UUID id) {
    ServiceResponse serviceResponse = commentService.findById(id);
    if(serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body("Cliente não encontrado");
  }

  @PostMapping
  public ResponseEntity<Object> createComment(@RequestBody @Validated Comment comment) {
    ServiceResponse serviceResponse = commentService.create(comment);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateComment(@PathVariable UUID id, @RequestBody Comment comment) {
    ServiceResponse serviceResponse = commentService.update(id, comment);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
    commentService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
