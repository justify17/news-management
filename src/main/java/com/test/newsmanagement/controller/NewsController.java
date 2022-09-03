package com.test.newsmanagement.controller;

import com.test.newsmanagement.model.entity.News;
import com.test.newsmanagement.model.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsRepository newsRepository;

    @GetMapping("/news")
    public ResponseEntity<?> getNews() {
        List<News> news  = newsRepository.findAll();

        return ResponseEntity.ok(news);
    }
}
