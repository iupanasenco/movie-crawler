package com.iunona.crawler.controller;

import com.iunona.crawler.AbstractTestConfig;
import com.iunona.crawler.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(AbstractTestConfig.class)
@AutoConfigureMockMvc
@WebMvcTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected MovieService movieService;

    @Test
    void crawlByCategory() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/crawl-movies-by-category?category=url2019"))
                .andExpect(status().isOk())
                .andReturn();
        String requestResult = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertNotNull(requestResult);
        assertFalse(requestResult.isEmpty());
    }

}