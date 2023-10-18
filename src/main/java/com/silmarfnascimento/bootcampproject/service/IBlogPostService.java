package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.BlogPost;

import java.util.List;
import java.util.UUID;

public interface IBlogPostService {
  ServiceResponse findAll();

  ServiceResponse findById(UUID id);

  ServiceResponse create(UUID authorId, BlogPost post);

  ServiceResponse addCategories(UUID id, UUID authorId, List<String> categoryName);

  ServiceResponse update(UUID id,UUID authorId, BlogPost post);

  ServiceResponse delete(UUID id);
}