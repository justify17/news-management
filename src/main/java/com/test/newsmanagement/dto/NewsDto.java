package com.test.newsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class NewsDto {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private String title;
    private String text;
    private List<CommentDto> comments;
}
