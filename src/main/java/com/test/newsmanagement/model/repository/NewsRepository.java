package com.test.newsmanagement.model.repository;

import com.test.newsmanagement.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByDate(LocalDate date);

    List<News> findByTitleContainingIgnoreCase(String title);

    List<News> findByTextContainingIgnoreCase(String text);
}
