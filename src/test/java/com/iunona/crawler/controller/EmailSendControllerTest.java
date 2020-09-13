package com.iunona.crawler.controller;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.repo.MoviesRepository;
import com.iunona.crawler.service.MailService;
import com.iunona.crawler.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
class EmailSendControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @TestConfiguration
    static class MovieServiceTestContextConfiguration {

        @Bean
        public JavaMailSender javaMailSender() {
            return new JavaMailSenderImpl();
        }

        @Bean
        public MoviesRepository moviesRepository() {
            return new MoviesRepository();
        }

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
            return new MovieService(moviesRepository(), movieCrawler(), mailService());
        }
    }

    //TODO:Implement Dumbster
//    @Test
//    void sendMail() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/send-movie-mail?mail=test_email@mail.md"))
//                .andExpect(status().isOk());
//    }

    @Test
    void sendMail_whenWrongMail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/send-movie-mail?mail=wrong_mail"))
                .andExpect(status().is5xxServerError());

    }
}