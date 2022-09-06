package com.test.newsmanagement.controller;

import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<Void> createNews(@RequestBody NewsDto newsDto) {
        newsService.save(newsDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNews(@PathVariable long id) {
        NewsDto newsDto = newsService.getById(id);

        return ResponseEntity.ok(newsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNews(@PathVariable long id, @RequestBody NewsDto newsDto) {
        newsService.update(id, newsDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable long id) {
        newsService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<List<NewsDto>> getNewsPage(@PathVariable int pageNumber) {
        List<NewsDto> news = newsService.getAllNewsByPage(pageNumber);

        return news.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(news);
    }

    @GetMapping("/{id}/comments/{commentsPageNumber}")
    public ResponseEntity<NewsDto> getNewsWithCommentsPage(@PathVariable long id, @PathVariable int commentsPageNumber) {
        NewsDto newsDto = newsService.getNewsByIdWithCommentsPage(id, commentsPageNumber);

        return ResponseEntity.ok(newsDto);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<NewsDto>> getNewsByDate(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        List<NewsDto> news = newsService.getNewsByDate(date);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<NewsDto>> getNewsByTitle(@PathVariable String title) {
        List<NewsDto> news = newsService.getNewsByTitle(title);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<NewsDto>> getNewsByText(@PathVariable String text) {
        List<NewsDto> news = newsService.getNewsByText(text);

        return ResponseEntity.ok(news);
    }
}
