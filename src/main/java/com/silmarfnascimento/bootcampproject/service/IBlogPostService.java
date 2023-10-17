package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.BlogPost;

import java.util.UUID;

public interface IBlogPostService {
  ServiceResponse findAll();

  ServiceResponse findById(UUID id);

  ServiceResponse create(BlogPost post);

  ServiceResponse update(UUID id, BlogPost post);

  void delete(UUID id);
}