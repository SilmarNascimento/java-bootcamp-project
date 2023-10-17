package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.Comment;

import java.util.UUID;

public interface ICommentService {
  ServiceResponse findAll();

  ServiceResponse findById(UUID id);

  ServiceResponse create(Comment comment);

  ServiceResponse update(UUID id, Comment comment);

  void delete(UUID id);
}
