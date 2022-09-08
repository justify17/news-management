package com.test.newsmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.newsmanagement.dto.CommentDto;
import com.test.newsmanagement.dto.ErrorResponse;
import com.test.newsmanagement.dto.NewsDto;
import com.test.newsmanagement.service.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NewsService newsService;

    @Test
    void whenCreateNews_AndValidRequest_thenExpected2xxResponse() throws Exception {
        NewsDto newsDto = new NewsDto("Title", "Text");
        String inputJson = objectMapper.writeValueAsString(newsDto);

        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).save(newsDto);
    }

    @Test
    void whenCreateNews_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        NewsDto newsDto = new NewsDto("", "");
        String inputJson = objectMapper.writeValueAsString(newsDto);

        List<String> messages = List.of("The field 'title' must not be empty", "The field 'text' must not be empty");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/news")
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(newsService, never()).save(newsDto);
    }

    @Test
    void whenGetNews_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        NewsDto newsDto = new NewsDto(LocalDate.now(), "Title", "Text", null);
        String outputJson = objectMapper.writeValueAsString(newsDto);

        when(newsService.getById(id)).thenReturn(newsDto);

        mockMvc.perform(get("/news/" + id)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getById(id);
    }

    @Test
    void whenUpdateNews_AndValidRequest_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        NewsDto newsDto = new NewsDto("Title", "Text");
        String inputJson = objectMapper.writeValueAsString(newsDto);

        mockMvc.perform(put("/news/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).update(id, newsDto);
    }

    @Test
    void whenUpdateNews_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        long id = 1L;

        NewsDto newsDto = new NewsDto("", "");
        String inputJson = objectMapper.writeValueAsString(newsDto);

        List<String> messages = List.of("The field 'title' must not be empty", "The field 'text' must not be empty");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(put("/news/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(newsService, never()).update(id, newsDto);
    }

    @Test
    void whenDeleteNews_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/news/" + id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(newsService, times(1)).deleteById(id);
    }

    @Test
    void whenGetNewsPage_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        int pageNumber = 1;

        List<NewsDto> news = List.of(new NewsDto(LocalDate.now(), "Title", "Text", null));
        String outputJson = objectMapper.writeValueAsString(news);

        when(newsService.getAllNewsByPage(pageNumber)).thenReturn(news);

        mockMvc.perform(get("/news/all/" + pageNumber)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getAllNewsByPage(pageNumber);
    }

    @Test
    void whenGetNewsPage_AndRequestIsFine_AndNewsNotFound_thenExpected2xxResponse() throws Exception {
        int pageNumber = 1;

        List<NewsDto> news = Collections.emptyList();

        when(newsService.getAllNewsByPage(pageNumber)).thenReturn(news);

        mockMvc.perform(get("/news/all/" + pageNumber)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(newsService, times(1)).getAllNewsByPage(pageNumber);
    }

    @Test
    void whenGetNewsWithCommentsPage_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        long id = 1;
        int pageNumber = 1;

        NewsDto newsDto = new NewsDto(LocalDate.now(), "Title", "Text", List.of(new CommentDto()));
        String outputJson = objectMapper.writeValueAsString(newsDto);

        when(newsService.getNewsByIdWithCommentsPage(id, pageNumber)).thenReturn(newsDto);

        mockMvc.perform(get("/news/" + id + "/comments/" + pageNumber)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getNewsByIdWithCommentsPage(id, pageNumber);
    }

    @Test
    void whenGetNewsByDate_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String pathVariableDate = "08.09.2022";
        LocalDate date = LocalDate.of(2022, 9, 8);

        List<NewsDto> news = List.of(new NewsDto(date, "Title", "Text", null));
        String outputJson = objectMapper.writeValueAsString(news);

        when(newsService.getNewsByDate(date)).thenReturn(news);

        mockMvc.perform(get("/news/date/" + pathVariableDate)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getNewsByDate(date);
    }

    @Test
    void whenGetNewsByTitle_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String title = "Title";

        List<NewsDto> news = List.of(new NewsDto(LocalDate.now(), title, "Text", null));
        String outputJson = objectMapper.writeValueAsString(news);

        when(newsService.getNewsByTitle(title)).thenReturn(news);

        mockMvc.perform(get("/news/title/" + title)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getNewsByTitle(title);
    }

    @Test
    void whenGetNewsByText_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        String text = "Text";

        List<NewsDto> news = List.of(new NewsDto(LocalDate.now(), "Title", text, null));
        String outputJson = objectMapper.writeValueAsString(news);

        when(newsService.getNewsByText(text)).thenReturn(news);

        mockMvc.perform(get("/news/text/" + text)
                        .contentType(APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(newsService, times(1)).getNewsByText(text);
    }
}