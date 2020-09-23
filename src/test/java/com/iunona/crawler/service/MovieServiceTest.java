package com.iunona.crawler.service;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.repo.MoviesRepository;
import com.iunona.crawler.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @TestConfiguration
    static class MovieServiceTestContextConfiguration {

        @Bean
        public MoviesRepository moviesRepository() {
            return new MoviesRepository();
        }

        @Bean
        public MovieCrawler movieCrawler(){
            return new MovieCrawler();
        }

        @Bean
        public MailService mailService(){
            return new MailService(new JavaMailSenderImpl());
        }

        @Bean
        public MovieService movieService(){
            return new MovieService(moviesRepository(), movieCrawler(), mailService());
        }


    }

    @Autowired
    private MovieService movieService;

    @Autowired
    private MoviesRepository moviesRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void crawlMovieList() {
        movieService.crawlMovieList("https://kinokrad.co/");
        assertFalse(moviesRepository.getMovies().isEmpty());
        assertEquals(12, moviesRepository.getMovies().size());
    }
}