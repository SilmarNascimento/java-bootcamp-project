package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Comment {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private UUID authorId;
  private String content;
  @CreationTimestamp
  private LocalDateTime publishedAt;
}
