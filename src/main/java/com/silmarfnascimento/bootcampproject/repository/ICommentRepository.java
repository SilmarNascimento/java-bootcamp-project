package com.silmarfnascimento.bootcampproject.repository;

import com.silmarfnascimento.bootcampproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICommentRepository extends JpaRepository<Comment, UUID> {
}
