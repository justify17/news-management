package com.test.newsmanagement.model.repository;

import com.test.newsmanagement.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
