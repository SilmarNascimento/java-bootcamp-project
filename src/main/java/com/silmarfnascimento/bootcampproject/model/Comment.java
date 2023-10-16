package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_comments")
public class Comment {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private UUID authorId;
  private UUID blogPostId;
  private String content;
  @CreationTimestamp
  private LocalDateTime publishedAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
