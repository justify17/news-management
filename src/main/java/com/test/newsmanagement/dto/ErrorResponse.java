package com.test.newsmanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private List<String> messages;

    public ErrorResponse(HttpStatus status, List<String> messages) {
        this.error = status.getReasonPhrase();
        this.messages = messages;
    }

    public ErrorResponse(String error, List<String> messages) {
        this.error = error;
        this.messages = messages;
    }
}