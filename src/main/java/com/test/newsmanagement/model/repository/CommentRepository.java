package com.test.newsmanagement.model.repository;

import com.test.newsmanagement.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByNews_Id(Long newsId, Pageable pageable);

    List<Comment> findByDate(LocalDate date);

    List<Comment> findByTextContainingIgnoreCase(String text);

    List<Comment> findByUsername(String username);
}
