package com.silmarfnascimento.bootcampproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tb_categories")
public class Category {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String category;
  @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
  @JsonIgnore
  private List<BlogPost> blogPosts;
}
