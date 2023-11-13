package com.silmarfnascimento.bootcampproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.silmarfnascimento.bootcampproject.model.Comment;
import com.silmarfnascimento.bootcampproject.service.Implementation.CommentService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/blogpost/{blogpostId}/comment")
@Tag(name = "CommentController")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping
  public ResponseEntity<Object> findAllCommentsByBlogPostId(@PathVariable UUID blogpostId) {
    ServiceResponse serviceResponse = commentService.findAllByBlogPostId(blogpostId);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findCommentById(UUID id) {
    ServiceResponse serviceResponse = commentService.findById(id);
    if (serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
          .body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body("Cliente não encontrado");
  }

  @Operation(summary = "Realiza o registro do usuário", method = "POST")
  @PostMapping
  public ResponseEntity<Object> createComment(HttpServletRequest request,
      @PathVariable UUID blogpostId, @RequestBody @Valid Comment comment) {
    UUID authorId = (UUID) request.getAttribute("idUser");
    ServiceResponse serviceResponse = commentService.create(authorId, blogpostId, comment);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateComment(@PathVariable UUID id, @RequestBody Comment comment) {
    ServiceResponse serviceResponse = commentService.update(id, comment);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteComment(@PathVariable UUID id) {
    ServiceResponse serviceResponse = commentService.delete(id);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }
}
