package com.silmarfnascimento.bootcampproject.repository;

import com.silmarfnascimento.bootcampproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {
}
