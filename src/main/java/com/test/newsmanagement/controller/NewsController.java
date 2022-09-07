package com.test.newsmanagement.controller;

import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Validated
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<Void> createNews(@Valid @RequestBody NewsDto newsDto) {
        newsService.save(newsDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNews(@Positive(message = "/news/{id} must be positive") @PathVariable long id) {
        NewsDto newsDto = newsService.getById(id);

        return ResponseEntity.ok(newsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNews(@Positive(message = "/news/{id} must be positive") @PathVariable long id,
                                           @Valid @RequestBody NewsDto newsDto) {
        newsService.update(id, newsDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@Positive(message = "/news/{id} must be positive") @PathVariable long id) {
        newsService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<List<NewsDto>> getNewsPage(@Positive(message = "/news/all/{pageNumber} must be positive")
                                                     @PathVariable int pageNumber) {
        List<NewsDto> news = newsService.getAllNewsByPage(pageNumber);

        return news.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(news);
    }

    @GetMapping("/{id}/comments/{pageNumber}")
    public ResponseEntity<NewsDto> getNewsWithCommentsPage(@Positive(message = "news/{id}/... must be positive")
                                                           @PathVariable long id,
                                                           @Positive(message = ".../comments/{pageNumber} must be positive")
                                                           @PathVariable int pageNumber) {
        NewsDto newsDto = newsService.getNewsByIdWithCommentsPage(id, pageNumber);

        return ResponseEntity.ok(newsDto);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<NewsDto>> getNewsByDate(@DateTimeFormat(pattern = "dd.MM.yyyy") @PathVariable LocalDate date) {
        List<NewsDto> news = newsService.getNewsByDate(date);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<NewsDto>> getNewsByTitle(@NotBlank(message = "news/title/{title} must not be empty")
                                                        @PathVariable String title) {
        List<NewsDto> news = newsService.getNewsByTitle(title);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<NewsDto>> getNewsByText(@NotBlank(message = "news/text/{text} must not be empty")
                                                       @PathVariable String text) {
        List<NewsDto> news = newsService.getNewsByText(text);

        return ResponseEntity.ok(news);
    }
}
