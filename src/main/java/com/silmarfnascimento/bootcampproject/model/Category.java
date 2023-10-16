package com.silmarfnascimento.bootcampproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Category {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String category;
}
