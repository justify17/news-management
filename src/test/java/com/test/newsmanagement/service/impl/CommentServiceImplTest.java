package com.test.newsmanagement.service.impl;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.exception.EntityByIdNotFoundException;
import com.test.newsmanagement.mapper.CommentMapper;
import com.test.newsmanagement.model.entity.Comment;
import com.test.newsmanagement.model.entity.News;
import com.test.newsmanagement.model.repository.CommentRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    private Comment comment;
    private CommentDto commentDto;

    @BeforeEach
    void init() {
        Long newsId = 1L;

        News news = new News();
        news.setId(newsId);

        comment = new Comment(LocalDate.now(), "Text", "User", news);
        commentDto = new CommentDto(LocalDate.now(), "Text", "User", newsId);
    }

    @Test
    void save() {
        when(commentMapper.commentDtoToComment(commentDto)).thenReturn(comment);

        commentService.save(commentDto);

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void getById_AndCommentExists_ReturnComment() {
        Long id = 1L;

        when(commentRepository.findById(id)).thenReturn(Optional.of(comment));
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        assertEquals(commentDto, commentService.getById(id));
    }

    @Test
    void getById_AndCommentDoesNotExist_ThrowException() {
        Long id = 1L;

        when(commentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityByIdNotFoundException.class, () -> commentService.getById(id));
    }

    @Test
    void update() {
        Long id = 1L;

        when(commentMapper.commentDtoToComment(commentDto)).thenReturn(comment);

        commentService.update(id, commentDto);

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void deleteById() {
        Long id = 10L;

        commentService.deleteById(id);

        verify(commentRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_AndCommentDoesNotExist_ThrowException() {
        Long id = 0L;

        doThrow(new EmptyResultDataAccessException(1)).when(commentRepository).deleteById(id);

        assertThrows(EntityByIdNotFoundException.class, () -> commentService.deleteById(id));
    }

    @Test
    void getCommentsPageByNewsId() {
        Long newsId = 1L;
        int commentsPageNumber = 1;

        Pageable pageable = PageRequest.of(commentsPageNumber, CommentServiceImpl.DEFAULT_ELEMENTS_NUMBER_PER_PAGINATION_PAGE);
        Page<Comment> commentsPage = new PageImpl<>(List.of(comment));

        when(commentRepository.findAllByNews_Id(newsId, pageable)).thenReturn(commentsPage);
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        assertEquals(List.of(commentDto), commentService.getCommentsPageByNewsId(newsId, commentsPageNumber));
    }

    @Test
    void getCommentsByDate() {
        LocalDate date = LocalDate.now();

        when(commentRepository.findByDate(date)).thenReturn(List.of(comment));
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        assertEquals(List.of(commentDto), commentService.getCommentsByDate(date));
    }

    @Test
    void getCommentsByText() {
        String text = "Text";

        when(commentRepository.findByTextContainingIgnoreCase(text)).thenReturn(List.of(comment));
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        assertEquals(List.of(commentDto), commentService.getCommentsByText(text));
    }

    @Test
    void getCommentsByUsername() {
        String username = "User";

        when(commentRepository.findByUsername(username)).thenReturn(List.of(comment));
        when(commentMapper.commentToCommentDto(comment)).thenReturn(commentDto);

        assertEquals(List.of(commentDto), commentService.getCommentsByUsername(username));
    }
}
