package com.test.newsmanagement.handler;

import com.test.newsmanagement.dto.ErrorResponse;
import com.test.newsmanagement.exception.EntityByIdNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String DEFAULT_ERROR_RESPONSE = "An error has occurred, please try again";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler({EntityByIdNotFoundException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleEntityByIdNotFoundException(RuntimeException exception) {
        HttpStatus status = BAD_REQUEST;
        List<String> messages = List.of(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> defaultExceptionHandler() {
        HttpStatus status = INTERNAL_SERVER_ERROR;
        List<String> messages = List.of(DEFAULT_ERROR_RESPONSE);

        ErrorResponse errorResponse = new ErrorResponse(status, messages);

        return new ResponseEntity<>(errorResponse, status);
    }
}