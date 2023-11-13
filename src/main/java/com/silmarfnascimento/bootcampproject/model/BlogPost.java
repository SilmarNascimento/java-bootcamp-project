package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Class BlogPost - Entity to represent a Post from a blog.
 */
@Data
@Entity
@Table(name = "blog_post")
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
  @ManyToMany
  @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "blog_post_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;
  @OneToMany(mappedBy = "blogPost", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments;

  @CreationTimestamp
  private LocalDateTime publishedAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
