package com.silmarfnascimento.bootcampproject.service.Implementation;

import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.service.IUserService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;

import java.util.UUID;

public class UserService implements IUserService {
  @Override
  public ServiceResponse findAll() {
    return null;
  }

  @Override
  public ServiceResponse findById(UUID id) {
    return null;
  }

  @Override
  public ServiceResponse create(User user) {
    return null;
  }

  @Override
  public ServiceResponse update(UUID id, User user) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
