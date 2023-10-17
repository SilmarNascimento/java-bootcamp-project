package com.silmarfnascimento.bootcampproject.service.Implementation;

import com.silmarfnascimento.bootcampproject.model.Category;
import com.silmarfnascimento.bootcampproject.service.ICategoryService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
  @Override
  public ServiceResponse findAll() {
    return null;
  }

  @Override
  public ServiceResponse findById(UUID id) {
    return null;
  }

  @Override
  public ServiceResponse create(Category category) {
    return null;
  }

  @Override
  public ServiceResponse update(UUID id, Category category) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
