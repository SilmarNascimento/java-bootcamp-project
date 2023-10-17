package com.silmarfnascimento.bootcampproject.service.Implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.silmarfnascimento.bootcampproject.model.Category;
import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.repository.ICategoryRepository;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import com.silmarfnascimento.bootcampproject.service.ICategoryService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import com.silmarfnascimento.bootcampproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
  @Autowired
  private ICategoryRepository categoryRepository;

  @Override
  public ServiceResponse findAll() {
    List<Category> categories = categoryRepository.findAll();
    return new ServiceResponse("OK", categories);
  }

  @Override
  public ServiceResponse findById(UUID id) {
    Optional<Category> category = categoryRepository.findById(id);
    if(category.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Usuário não encontrado");
    }
    return new ServiceResponse("OK", category);
  }

  @Override
  public ServiceResponse create(Category category) {
    Optional<Category> categoryFound = this.categoryRepository.findByCategory(category.getCategory());
    if (categoryFound.isPresent()) {
      return new ServiceResponse("BAD_REQUEST", "Categoria já existente!");
    }
    Category createdCategory = categoryRepository.save(category);
    return new ServiceResponse("CREATED", createdCategory);
  }

  @Override
  public ServiceResponse update(UUID id, Category category) {
    Optional<Category> categoryFound = categoryRepository.findById(id);
    if (categoryFound.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Client not Found");
    }
    Category updatedCategory = categoryFound.get();
    updatedCategory.setCategory(category.getCategory());
    return new ServiceResponse("OK", categoryRepository.save(updatedCategory));
  }

  @Override
  public void delete(UUID id) {
    categoryRepository.deleteById(id);
  }
}
