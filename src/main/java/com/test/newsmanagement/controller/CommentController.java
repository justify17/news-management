package com.test.newsmanagement.controller;

import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long id) {
        CommentDto commentDto = commentService.getById(id);

        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto) {
        commentService.update(id, commentDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        commentService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CommentDto>> getCommentsByDate(@PathVariable @DateTimeFormat(pattern = "dd.MM.yyyy")
                                                                      LocalDate date) {
        List<CommentDto> comments = commentService.getCommentsByDate(date);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/text/{text}")
    public ResponseEntity<List<CommentDto>> getCommentsByText(@PathVariable String text) {
        List<CommentDto> comments = commentService.getCommentsByText(text);

        return ResponseEntity.ok(comments);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<CommentDto>> getCommentsByUsername(@PathVariable String username) {
        List<CommentDto> comments = commentService.getCommentsByUsername(username);

        return ResponseEntity.ok(comments);
    }
}
