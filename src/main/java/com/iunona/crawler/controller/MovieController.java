package com.iunona.crawler.controller;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.model.Movie;
import com.iunona.crawler.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final MovieService movieService;

    @GetMapping("/crawl-movies-by-category")
    public ResponseEntity<List<Movie>> crawlByCategory(@RequestParam String category) {
        movieService.crawlMovieList(categoryToUrl.get(category));
        System.out.println(movieService.getMovieList());
        return new ResponseEntity<>(movieService.getMovieList(), HttpStatus.OK);
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
