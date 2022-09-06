package com.test.newsmanagement.service.impl;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.exception.EntityByIdNotFoundException;
import com.test.newsmanagement.mapper.CommentMapper;
import com.test.newsmanagement.model.entity.Comment;
import com.test.newsmanagement.model.repository.CommentRepository;
import com.test.newsmanagement.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public void save(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);

        commentRepository.save(comment);
    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityByIdNotFoundException(Comment.class, id));

        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public void update(Long id, CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        comment.setId(id);

        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        try {
            commentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityByIdNotFoundException(Comment.class, id);
        }
    }

    @Override
    public List<CommentDto> getCommentsPageByNewsId(Long newsId, int commentsPageNumber) {
        Pageable pageable = PageRequest.of(commentsPageNumber, DEFAULT_ELEMENTS_NUMBER_PER_PAGINATION_PAGE);

        Page<Comment> comments = commentRepository.findAllByNews_Id(newsId, pageable);

        return comments.map(commentMapper::commentToCommentDto)
                .getContent();
    }

    @Override
    public List<CommentDto> getCommentsByDate(LocalDate date) {
        List<Comment> comments = commentRepository.findByDate(date);

        return getCommentsDtoFromComments(comments);
    }

    private List<CommentDto> getCommentsDtoFromComments(List<Comment> comments) {

        return comments.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByText(String text) {
        List<Comment> comments = commentRepository.findByTextContainingIgnoreCase(text);

        return getCommentsDtoFromComments(comments);
    }

    @Override
    public List<CommentDto> getCommentsByUsername(String username) {
        List<Comment> comments = commentRepository.findByUsername(username);

        return getCommentsDtoFromComments(comments);
    }
}
