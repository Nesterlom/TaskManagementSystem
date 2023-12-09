package com.taskmanagementsystem.taskmanagementsystem.repository;

import com.taskmanagementsystem.taskmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Optional<Comment> getCommentById(Long id);
}
