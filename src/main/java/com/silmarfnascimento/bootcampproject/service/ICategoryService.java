package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.Category;

import java.util.UUID;

public interface ICategoryService {
  ServiceResponse findAll();

  ServiceResponse findById(UUID id);

  ServiceResponse create(Category category);

  ServiceResponse update(UUID id, Category category);

  ServiceResponse delete(UUID id);
}
