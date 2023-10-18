package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.Comment;

import java.util.UUID;

public interface ICommentService {
  ServiceResponse findAllByBlogPostId(UUID id);

  ServiceResponse findById(UUID id);

  ServiceResponse create(UUID authorId, UUID blogpostId, Comment comment);

  ServiceResponse update(UUID id, Comment comment);

  ServiceResponse delete(UUID id);
}
