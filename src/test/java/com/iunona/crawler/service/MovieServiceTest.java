package com.iunona.crawler.service;

import com.iunona.crawler.AbstractTestConfig;
import com.iunona.crawler.repo.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Import(AbstractTestConfig.class)
@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void crawlMovieList() {
        movieService.crawlMovieList("https://kinokrad.co/");
        assertFalse(movieRepository.findAll().isEmpty());
        assertEquals(12, movieRepository.findAll().size());
    }
}