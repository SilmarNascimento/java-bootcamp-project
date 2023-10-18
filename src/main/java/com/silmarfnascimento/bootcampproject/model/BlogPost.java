package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "BLOGPOST")
public class BlogPost {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  @Column(name = "author_id")
  private UUID authorId;
  private String title;
  private String description;
  private String content;
  private String image255;
  private String image825;
  private String image1800;
  @OneToMany
  private List<Category> categories;
  @OneToMany(mappedBy = "blogPost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;
  @CreationTimestamp
  private LocalDateTime publishedAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
