package com.silmarfnascimento.bootcampproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_categories")
public class Category {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String category;
  @ManyToOne
  @JoinColumn(name = "blog_post_id")
  @JsonIgnore
  private BlogPost blogPost;
}
