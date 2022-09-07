package com.test.newsmanagement.controller;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentDto commentDto) {
        commentService.save(commentDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@Positive(message = "/comments/{id} must be positive") @PathVariable long id) {
        CommentDto commentDto = commentService.getById(id);

        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@Positive(message = "/comments/{id} must be positive") @PathVariable long id,
                                              @Valid @RequestBody CommentDto commentDto) {
        commentService.update(id, commentDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@Positive(message = "/comments/{id} must be positive") @PathVariable long id) {
        commentService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CommentDto>> getCommentsByDate(@DateTimeFormat(pattern = "dd.MM.yyyy") @PathVariable LocalDate date) {
        List<CommentDto> comments = commentService.getCommentsByDate(date);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<CommentDto>> getCommentsByText(@NotBlank(message = "comments/text/{text} must not be empty")
                                                              @PathVariable String text) {
        List<CommentDto> comments = commentService.getCommentsByText(text);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<CommentDto>> getCommentsByUsername(@NotBlank(message = "comments/username/{username} must not be empty")
                                                                  @PathVariable String username) {
        List<CommentDto> comments = commentService.getCommentsByUsername(username);

        return ResponseEntity.ok(comments);
    }
}