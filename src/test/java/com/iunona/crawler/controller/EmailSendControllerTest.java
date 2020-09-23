package com.iunona.crawler.controller;

import com.iunona.crawler.AbstractTestConfig;
import com.iunona.crawler.service.MailService;
import com.iunona.crawler.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(AbstractTestConfig.class)
@AutoConfigureMockMvc
@WebMvcTest
class EmailSendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected MovieService movieService;

    @Autowired
    protected MailService mailService;


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