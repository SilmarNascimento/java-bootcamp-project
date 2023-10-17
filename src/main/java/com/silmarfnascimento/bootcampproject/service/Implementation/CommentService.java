package com.silmarfnascimento.bootcampproject.service.Implementation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.silmarfnascimento.bootcampproject.model.Comment;
import com.silmarfnascimento.bootcampproject.model.User;
import com.silmarfnascimento.bootcampproject.repository.ICommentRepository;
import com.silmarfnascimento.bootcampproject.repository.IUserRepository;
import com.silmarfnascimento.bootcampproject.service.ICommentService;
import com.silmarfnascimento.bootcampproject.service.ServiceResponse;
import com.silmarfnascimento.bootcampproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {
  @Autowired
  private ICommentRepository commentRepository;

  @Override
  public ServiceResponse findAllByBlogPostId(UUID postId) {
    List<Comment> comments = commentRepository.findByBlogPostId(postId);
    return new ServiceResponse("OK", comments);
  }

  @Override
  public ServiceResponse findById(UUID id) {
    Optional<Comment> comment = commentRepository.findById(id);
    if(comment.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Usuário não encontrado");
    }
    return new ServiceResponse("OK", comment);
  }

  @Override
  public ServiceResponse create(Comment comment) {
    Comment createdComment = commentRepository.save(comment);
    return new ServiceResponse("CREATED", createdComment);
  }

  @Override
  public ServiceResponse update(UUID id, Comment comment) {
    Optional<Comment> commentFound = commentRepository.findById(id);
    if (commentFound.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Comment not Found");
    }

    Comment commentContent = new Comment();
    commentContent.setContent(comment.getContent());

    Utils.copyNonNullProperties(commentContent, commentFound.get());
    Comment updatedComment = commentRepository.save(commentFound.get());
    return new ServiceResponse("OK", updatedComment);
  }

  @Override
  public void delete(UUID id) {
    commentRepository.deleteById(id);
  }

  private static void hashUserPassword(User user) {
    String passwordHashed = BCrypt.withDefaults()
        .hashToString(12, user.getPassword().toCharArray());
    user.setPassword(passwordHashed);
  }
}

