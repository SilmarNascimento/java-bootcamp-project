package com.silmarfnascimento.bootcampproject.service.Implementation;

import com.silmarfnascimento.bootcampproject.model.BlogPost;
import com.silmarfnascimento.bootcampproject.model.Category;
import com.silmarfnascimento.bootcampproject.model.Comment;
import com.silmarfnascimento.bootcampproject.repository.IBlogPostRepository;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import com.silmarfnascimento.bootcampproject.service.IBlogPostService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import com.silmarfnascimento.bootcampproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService implements IBlogPostService {
  @Autowired
  private IBlogPostRepository blogPostRepository;
  @Autowired
  private IUserRepository userRepository;

  @Override
  public ServiceResponse findAll() {
    List<BlogPost> posts = blogPostRepository.findAll();
    return new ServiceResponse("OK", posts);
  }

  @Override
  public ServiceResponse findById(UUID id) {
    Optional<BlogPost> post = blogPostRepository.findById(id);
    if(post.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Post not found");
    }
    return new ServiceResponse("OK", post);
  }

  @Override
  public ServiceResponse create(UUID authorId, BlogPost post) {
    post.setAuthorId(authorId);
    post.setCategories(new ArrayList<Category>());
    post.setComments(new ArrayList<Comment>());
    post.setImage255(post.getImage255() != null && !post.getImage255().isEmpty() ? post.getImage255() : "");
    post.setImage825(post.getImage825() != null && !post.getImage825().isEmpty() ? post.getImage825() : "");
    post.setImage1800(post.getImage255() != null && !post.getImage1800().isEmpty() ? post.getImage1800() : "");
    if(post.getDescription() == null || post.getContent() == null || post.getTitle() == null) {
      return new ServiceResponse("BAD_REQUEST", "There is missing information");
    }
    BlogPost createdPost = blogPostRepository.save(post);
    return new ServiceResponse("CREATED", createdPost);
  }

  @Override
  public ServiceResponse update(UUID id, UUID authorId, BlogPost post) {
    Optional<BlogPost> postFound = blogPostRepository.findById(id);
    if(postFound.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Post not Found");
    }
    if(postFound.get().getAuthorId() != authorId) {
      return new ServiceResponse("FORBIDDEN", "Post doesn't belong to this user");
    }
    if(post.getDescription() == null || post.getContent() == null || post.getTitle() == null) {
      return new ServiceResponse("BAD_REQUEST", "There is missing information");
    }
    Utils.copyNonNullProperties(post, postFound.get());
    BlogPost updatedPost = blogPostRepository.save(postFound.get());
    return new ServiceResponse("OK", updatedPost);
  }

  @Override
  public void delete(UUID id) {
    blogPostRepository.deleteById(id);
  }
}
