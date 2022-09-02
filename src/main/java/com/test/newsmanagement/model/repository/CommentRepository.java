package com.test.newsmanagement.model.repository;

import com.test.newsmanagement.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
