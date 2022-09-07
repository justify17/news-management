package com.test.newsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CommentDto {

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Null(message = "The field 'date' must be empty")
    private LocalDate date;

    @NotBlank(message = "The field 'text' must not be empty")
    private String text;

    @NotBlank(message = "The field 'username' must not be empty")
    private String username;

    @Positive(message = "The field 'newsId' is required and must be positive")
    private Long newsId;

    public CommentDto(String text, String username, Long newsId) {
        this.text = text;
        this.username = username;
        this.newsId = newsId;
    }

    public CommentDto(LocalDate date, String text, String username, Long newsId) {
        this.date = date;
        this.text = text;
        this.username = username;
        this.newsId = newsId;
    }
}
