package com.test.newsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDto {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private String text;
    private String username;
    private Long newsId;
}
