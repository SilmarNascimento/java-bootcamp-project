package com.silmarfnascimento.bootcampproject.service.Implementation;

import com.silmarfnascimento.bootcampproject.model.Comment;
import com.silmarfnascimento.bootcampproject.repository.IBlogPostRepository;
import com.silmarfnascimento.bootcampproject.repository.ICommentRepository;
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

  @Autowired
  private IBlogPostRepository blogPostRepository;

  @Override
  public ServiceResponse findAllByBlogPostId(UUID postId) {
    List<Comment> comments = commentRepository.findByBlogPostId(postId);
    return new ServiceResponse("OK", comments);
  }

  @Override
  public ServiceResponse findById(UUID id) {
    Optional<Comment> comment = commentRepository.findById(id);
    if(comment.isEmpty()) {
      return new ServiceResponse("NOT_FOUND", "Comment not Found");
    }
    return new ServiceResponse("OK", comment);
  }

  @Override
  public ServiceResponse create(UUID authorId, UUID blogPostId, Comment comment) {
    comment.setAuthorId(authorId);
    comment.setBlogPost(blogPostRepository.findById(blogPostId).orElse(null));
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
  public ServiceResponse delete(UUID id) {
    Optional<Comment> commentFound = commentRepository.findById(id);
    if (commentFound.isPresent()) {
      commentFound.get().setBlogPost(null);
      commentRepository.save(commentFound.get()); // Update the database
      commentRepository.deleteById(id);
      return new ServiceResponse("NO_CONTENT", "Comment deleted");
    }
    return new ServiceResponse("NOT_FOUND", "Comment not Found");
  }
}

