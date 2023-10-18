package com.silmarfnascimento.bootcampproject.controller;

import com.silmarfnascimento.bootcampproject.model.BlogPost;
import com.silmarfnascimento.bootcampproject.service.Implementation.BlogPostService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
  @Autowired
  private BlogPostService blogPostService;

  @GetMapping
  public ResponseEntity<Object> findAllBlogPosts() {
    ServiceResponse serviceResponse = blogPostService.findAll();
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findBlogPostById(UUID id) {
    ServiceResponse serviceResponse = blogPostService.findById(id);
    if(serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body("Cliente não encontrado");
  }

  @PostMapping
  public ResponseEntity<Object> createBlogPost(HttpServletRequest request, @RequestBody @Valid BlogPost post) {
    UUID authorId = (UUID) request.getAttribute("idUser");
    ServiceResponse serviceResponse = blogPostService.create(authorId, post);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateBlogPost(HttpServletRequest request, @PathVariable UUID id, @RequestBody BlogPost post) {
    UUID authorId = (UUID) request.getAttribute("idUser");
    ServiceResponse serviceResponse = blogPostService.update(id, authorId, post);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCategory(@PathVariable UUID id) {
    ServiceResponse serviceResponse = blogPostService.delete(id);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus())).body(serviceResponse.getData());

  }
}
