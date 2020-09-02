package com.iunona.crawler.controller;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.model.Movie;
import com.iunona.crawler.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@PropertySource("classpath:movies.properties")
public class MovieController {

    @Value("#{${movie-crawler.category-to-url}}")
    private final Map<String, String> categoryToUrl;

    private final MovieCrawler movieCrawler;
    private final MovieService movieService;

    @GetMapping("/category-choice")
    public void crawlByCategory(@RequestParam String category) {
        movieCrawler.crawl(categoryToUrl.get(category));
    }

    @GetMapping("/send-movie-mail")
    public void sendMail() {
        movieService.sendMovieMail();
    }

    @GetMapping("/show-movie-list")
    public List<Movie> showMovieList() {
        return movieService.getMovieList();
    }
}
