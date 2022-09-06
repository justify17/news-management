package com.test.newsmanagement.service;

import com.test.newsmanagement.dto.CommentDto;

import java.time.LocalDate;
import java.util.List;

public interface CommentService extends DefaultService<CommentDto> {
    List<CommentDto> getCommentsPageByNewsId(Long newsId, int commentsPageNumber);

    List<CommentDto> getCommentsByDate(LocalDate date);

    List<CommentDto> getCommentsByText(String text);

    List<CommentDto> getCommentsByUsername(String username);
}
