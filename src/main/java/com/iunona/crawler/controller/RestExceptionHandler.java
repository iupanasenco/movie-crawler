package com.iunona.crawler.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<MovieCrawlerException> handleRestException() {
        return new ResponseEntity<>(new MovieCrawlerException("There is no such category"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class MovieCrawlerException {
        private String message;
    }

}
