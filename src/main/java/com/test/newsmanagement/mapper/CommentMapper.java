package com.test.newsmanagement.mapper;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.model.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "news.id", target = "newsId")
    CommentDto commentToCommentDto(Comment comment);

    @InheritInverseConfiguration
    Comment commentDtoToComment(CommentDto commentDto);
}
