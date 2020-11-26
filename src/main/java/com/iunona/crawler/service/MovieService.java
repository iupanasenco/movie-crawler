package com.iunona.crawler.service;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.model.Movie;
import com.iunona.crawler.repo.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieCrawler movieCrawler;

    @Autowired
    private MailService mailService;

    public void addMovie(Movie movie) {
        if (validateMovie())
            movieRepository.save(movie);
    }

    public void crawlMovieList(String url) {
        List<Movie> movies = movieCrawler.crawl(url);
        movies.forEach(movieRepository::save);
    }

    public void sendMovieMail(String mail) {
        mailService.sendMovieMail(movieRepository.findAll(), mail);
    }

    public List<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    //TODO: write validating logic
    private boolean validateMovie() {
        return true;
    }
}
