package com.silmarfnascimento.bootcampproject.service;

import com.silmarfnascimento.bootcampproject.model.User;

import java.util.UUID;

public interface IUserService {
  ServiceResponse findAll();

  ServiceResponse findById(UUID id);

  ServiceResponse create(User user);

  ServiceResponse update(UUID id, User user);

  ServiceResponse delete(UUID id);
}
