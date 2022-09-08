package com.test.newsmanagement.service.impl;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.exception.EntityByIdNotFoundException;
import com.test.newsmanagement.mapper.NewsMapper;
import com.test.newsmanagement.model.entity.News;
import com.test.newsmanagement.model.repository.NewsRepository;
import com.test.newsmanagement.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private CommentService commentService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsMapper newsMapper;

    private News news;
    private NewsDto newsDto;

    @BeforeEach
    void init() {
        news = new News(LocalDate.now(), "Title", "Text");
        newsDto = new NewsDto(LocalDate.now(), "Title", "Text", null);
    }

    @Test
    void save() {
        when(newsMapper.newsDtoToNews(newsDto)).thenReturn(news);

        newsService.save(newsDto);

        verify(newsRepository, times(1)).save(news);
    }

    @Test
    void getById_AndNewsExists_ReturnNews() {
        Long id = 1L;

        when(newsRepository.findById(id)).thenReturn(Optional.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        assertEquals(newsDto, newsService.getById(id));
    }

    @Test
    void getById_AndNewsDoesNotExist_ThrowException() {
        Long id = 1L;

        when(newsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityByIdNotFoundException.class, () -> newsService.getById(id));
    }

    @Test
    void update() {
        Long id = 1L;

        when(newsMapper.newsDtoToNews(newsDto)).thenReturn(news);

        newsService.update(id, newsDto);

        verify(newsRepository, times(1)).save(news);
    }

    @Test
    void deleteById() {
        Long id = 10L;

        newsService.deleteById(id);

        verify(newsRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_AndNewsDoesNotExist_ThrowException() {
        Long id = 0L;

        doThrow(new EmptyResultDataAccessException(1)).when(newsRepository).deleteById(id);

        assertThrows(EntityByIdNotFoundException.class, () -> newsService.deleteById(id));
    }

    @Test
    void getAllNewsByPage() {
        int pageNumber = 1;

        Page<News> newsPage = new PageImpl<>(List.of(news));

        when(newsRepository.findAll(any(Pageable.class))).thenReturn(newsPage);
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        assertEquals(List.of(newsDto), newsService.getAllNewsByPage(pageNumber));
    }

    @Test
    void getNewsByIdWithCommentsPage() {
        Long newsId = 1L;
        int commentsPageNumber = 1;

        List<CommentDto> comments = List.of(new CommentDto("Text", "User", newsId));

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);
        when(commentService.getCommentsPageByNewsId(newsId, commentsPageNumber)).thenReturn(comments);

        assertEquals(newsDto, newsService.getNewsByIdWithCommentsPage(newsId, commentsPageNumber));
    }

    @Test
    void getNewsByDate() {
        LocalDate date = LocalDate.now();

        when(newsRepository.findByDate(date)).thenReturn(List.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        assertEquals(List.of(newsDto), newsService.getNewsByDate(date));
    }

    @Test
    void getNewsByTitle() {
        String title = "Title";

        when(newsRepository.findByTitleContainingIgnoreCase(title)).thenReturn(List.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        assertEquals(List.of(newsDto), newsService.getNewsByTitle(title));
    }

    @Test
    void getNewsByText() {
        String text = "Text";

        when(newsRepository.findByTextContainingIgnoreCase(text)).thenReturn(List.of(news));
        when(newsMapper.newsToNewsDto(news)).thenReturn(newsDto);

        assertEquals(List.of(newsDto), newsService.getNewsByText(text));
    }
}
