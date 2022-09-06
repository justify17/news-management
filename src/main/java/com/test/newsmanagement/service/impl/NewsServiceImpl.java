package com.test.newsmanagement.service.impl;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.exception.EntityByIdNotFoundException;
import com.test.newsmanagement.mapper.NewsMapper;
import com.test.newsmanagement.model.entity.News;
import com.test.newsmanagement.model.repository.NewsRepository;
import com.test.newsmanagement.service.CommentService;
import com.test.newsmanagement.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final CommentService commentService;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public void save(NewsDto newsDto) {
        News news = newsMapper.newsDtoToNews(newsDto);

        newsRepository.save(news);
    }

    @Override
    public NewsDto getById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityByIdNotFoundException(News.class, id));

        return newsMapper.newsToNewsDto(news);
    }

    @Override
    public void update(Long id, NewsDto newsDto) {
        News news = newsMapper.newsDtoToNews(newsDto);
        news.setId(id);

        newsRepository.save(news);
    }

    @Override
    public void deleteById(Long id) {
        try {
            newsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityByIdNotFoundException(News.class, id);
        }
    }

    @Override
    public List<NewsDto> getAllNewsByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, DEFAULT_ELEMENTS_NUMBER_PER_PAGINATION_PAGE);

        Page<News> news = newsRepository.findAll(pageable);

        return news.map(newsMapper::newsToNewsDto)
                .getContent();
    }

    @Override
    public NewsDto getNewsByIdWithCommentsPage(Long newsId, int commentsPageNumber) {
        NewsDto newsDto = getById(newsId);

        List<CommentDto> comments = commentService.getCommentsPageByNewsId(newsId, commentsPageNumber);

        newsDto.setComments(comments);

        return newsDto;
    }

    @Override
    public List<NewsDto> getNewsByDate(LocalDate date) {
        List<News> news = newsRepository.findByDate(date);

        return getNewsDtoFromNews(news);
    }

    private List<NewsDto> getNewsDtoFromNews(List<News> news) {

        return news.stream()
                .map(newsMapper::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getNewsByTitle(String title) {
        List<News> news = newsRepository.findByTitleContainingIgnoreCase(title);

        return getNewsDtoFromNews(news);
    }

    @Override
    public List<NewsDto> getNewsByText(String text) {
        List<News> news = newsRepository.findByTextContainingIgnoreCase(text);

        return getNewsDtoFromNews(news);
    }
}
