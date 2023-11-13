package com.silmarfnascimento.bootcampproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.silmarfnascimento.bootcampproject.model.Category;
import com.silmarfnascimento.bootcampproject.service.Implementation.CategoryService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.silmarfnascimento.bootcampproject.utils.mapHTTPStatus.mapHttpStatus;

@RestController
@RequestMapping("/category")
@Tag(name = "CategoryController")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Object> findAllCategories() {
    ServiceResponse serviceResponse = categoryService.findAll();
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findCategoryById(UUID id) {
    ServiceResponse serviceResponse = categoryService.findById(id);
    if (serviceResponse.getData() != null) {
      return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
          .body(serviceResponse.getData());
    }
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body("Cliente não encontrado");
  }

  @Operation(summary = "Realiza o registro do usuário", method = "POST")
  @PostMapping
  public ResponseEntity<Object> createCategory(@RequestBody @Valid Category category) {
    ServiceResponse serviceResponse = categoryService.create(category);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCategory(@PathVariable UUID id,
      @RequestBody Category category) {
    ServiceResponse serviceResponse = categoryService.update(id, category);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCategory(@PathVariable UUID id) {
    ServiceResponse serviceResponse = categoryService.delete(id);
    return ResponseEntity.status(mapHttpStatus(serviceResponse.getStatus()))
        .body(serviceResponse.getData());

  }
}
