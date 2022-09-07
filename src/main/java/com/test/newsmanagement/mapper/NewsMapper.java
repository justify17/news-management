package com.test.newsmanagement.mapper;

import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.model.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "comments", ignore = true)
    NewsDto newsToNewsDto(News news);

    @Mapping(target = "date", ignore = true)
    @Mapping(target = "comments", ignore = true)
    News newsDtoToNews(NewsDto newsDto);
}
