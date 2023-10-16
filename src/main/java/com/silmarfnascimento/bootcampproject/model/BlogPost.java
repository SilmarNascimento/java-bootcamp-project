package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_blog_posts")
public class BlogPost {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String title;
  private String content;
  @Column(name = "user_id")
  private UUID userId;
  @OneToMany
  private List<Category> categories;
  @CreationTimestamp
  private LocalDateTime publishedAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
