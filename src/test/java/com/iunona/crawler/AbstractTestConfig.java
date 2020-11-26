package com.iunona.crawler;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.repo.MovieRepository;
import com.iunona.crawler.service.MailService;
import com.iunona.crawler.service.MovieService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@TestConfiguration
public class AbstractTestConfig {

    MovieRepository movieRepository;

    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

//    @Bean
//    public MovieRepository moviesRepository() {
//        return new MovieRepository();
//    }

    @Bean
    public MovieCrawler movieCrawler() {
        return new MovieCrawler();
    }

    @Bean
    public MailService mailService() {
        return new MailService(javaMailSender());
    }

    @Bean
    public MovieService movieService() {
        return new MovieService(movieRepository, movieCrawler(), mailService());
    }

}
