package com.test.newsmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.dto.ErrorResponse;
import com.test.newsmanagement.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @Test
    void whenCreateComment_AndValidRequest_thenExpected2xxResponse() throws Exception {
        CommentDto commentDto = new CommentDto("Text", "Username", 1L);
        String inputJson = objectMapper.writeValueAsString(commentDto);

        mockMvc.perform(post("/comments")
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).save(commentDto);
    }

    @Test
    void whenCreateComment_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        CommentDto commentDto = new CommentDto("", "", -10L);
        String inputJson = objectMapper.writeValueAsString(commentDto);

        List<String> messages = List.of(
                "The field 'text' must not be empty",
                "The field 'username' must not be empty",
                "The field 'newsId' is required and must be positive");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/comments")
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(commentService, never()).save(commentDto);
    }

    @Test
    void whenGetComment_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        CommentDto commentDto = new CommentDto(LocalDate.now(), "Text", "Username", 1L);
        String outputJson = objectMapper.writeValueAsString(commentDto);

        when(commentService.getById(id)).thenReturn(commentDto);

        mockMvc.perform(get("/comments/" + id)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).getById(id);
    }

    @Test
    void whenUpdateComment_AndValidRequest_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        CommentDto commentDto = new CommentDto("Text", "Username", 1L);
        String inputJson = objectMapper.writeValueAsString(commentDto);

        mockMvc.perform(put("/comments/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).update(id, commentDto);
    }

    @Test
    void whenUpdateComment_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        long id = 1L;

        CommentDto commentDto = new CommentDto("", "", -10L);
        String inputJson = objectMapper.writeValueAsString(commentDto);

        List<String> messages = List.of(
                "The field 'text' must not be empty",
                "The field 'username' must not be empty",
                "The field 'newsId' is required and must be positive");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(put("/comments/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(commentService, never()).update(id, commentDto);
    }

    @Test
    void whenDeleteComment_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/comments/" + id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(commentService, times(1)).deleteById(id);
    }

    @Test
    void whenGetCommentsByDate_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String pathVariableDate = "08.09.2022";
        LocalDate date = LocalDate.of(2022, 9, 8);

        List<CommentDto> comments = List.of(new CommentDto(date, "Text", "Username", 1L));
        String outputJson = objectMapper.writeValueAsString(comments);

        when(commentService.getCommentsByDate(date)).thenReturn(comments);

        mockMvc.perform(get("/comments/date/" + pathVariableDate)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).getCommentsByDate(date);
    }

    @Test
    void whenGetCommentsByText_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String text = "Text";

        List<CommentDto> comments = List.of(new CommentDto(LocalDate.now(), text, "Username", 1L));
        String outputJson = objectMapper.writeValueAsString(comments);

        when(commentService.getCommentsByText(text)).thenReturn(comments);

        mockMvc.perform(get("/comments/text/" + text)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).getCommentsByText(text);
    }

    @Test
    void whenGetCommentsByUsername_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String username = "Username";

        List<CommentDto> comments = List.of(new CommentDto(LocalDate.now(), "Text", username, 1L));
        String outputJson = objectMapper.writeValueAsString(comments);

        when(commentService.getCommentsByUsername(username)).thenReturn(comments);

        mockMvc.perform(get("/comments/username/" + username)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(commentService, times(1)).getCommentsByUsername(username);
    }
}
