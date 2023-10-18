package com.silmarfnascimento.bootcampproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
  @ManyToOne
  @JoinColumn(name = "blog_post_id")
  @JsonIgnore
  private BlogPost blogPost;
  private String content;
  @CreationTimestamp
  private LocalDateTime publishedAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
