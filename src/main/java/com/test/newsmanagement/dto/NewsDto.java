package com.test.newsmanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class NewsDto {

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Null(message = "The field 'date' must be empty")
    private LocalDate date;

    @NotBlank(message = "The field 'title' must not be empty")
    private String title;

    @NotBlank(message = "The field 'text' must not be empty")
    private String text;

    @Null(message = "The field 'comments' must be empty")
    private List<CommentDto> comments;

    public NewsDto(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public NewsDto(LocalDate date, String title, String text, List<CommentDto> comments) {
        this.date = date;
        this.title = title;
        this.text = text;
        this.comments = comments;
    }
}