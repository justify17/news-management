package com.test.newsmanagement.mapper;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "news.id", target = "newsId")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "newsId", target = "news.id")
    @Mapping(target = "date", ignore = true)
    Comment commentDtoToComment(CommentDto commentDto);
}
