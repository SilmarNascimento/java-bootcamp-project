package com.silmarfnascimento.bootcampproject.service.Implementation;

import com.silmarfnascimento.bootcampproject.model.BlogPost;
import com.silmarfnascimento.bootcampproject.service.IBlogPostService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogPostService implements IBlogPostService {
  @Override
  public ServiceResponse findAll() {
    return null;
  }

  @Override
  public ServiceResponse findById(UUID id) {
    return null;
  }

  @Override
  public ServiceResponse create(BlogPost post) {
    return null;
  }

  @Override
  public ServiceResponse update(UUID id, BlogPost post) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
