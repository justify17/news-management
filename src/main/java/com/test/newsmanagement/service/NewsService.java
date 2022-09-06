package com.test.newsmanagement.service;

import com.test.newsmanagement.dto.NewsDto;

import java.time.LocalDate;
import java.util.List;

public interface NewsService extends DefaultService<NewsDto> {
    List<NewsDto> getAllNewsByPage(int pageNumber);

    NewsDto getNewsByIdWithCommentsPage(Long newsId, int commentsPageNumber);

    List<NewsDto> getNewsByDate(LocalDate date);

    List<NewsDto> getNewsByTitle(String title);

    List<NewsDto> getNewsByText(String text);
}
