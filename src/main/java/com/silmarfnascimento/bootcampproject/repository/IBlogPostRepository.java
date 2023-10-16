package com.silmarfnascimento.bootcampproject.repository;

import com.silmarfnascimento.bootcampproject.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
