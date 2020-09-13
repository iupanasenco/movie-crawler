package com.iunona.crawler.service;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.model.Movie;
import com.iunona.crawler.repo.MoviesRepository;
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
    private MoviesRepository moviesRepository;

    @Autowired
    private MovieCrawler movieCrawler;

    @Autowired
    private MailService mailService;

    public void addMovie(Movie movie) {
        if (validateMovie())
            moviesRepository.addMovie(movie);
    }

    public void crawlMovieList(String url) {
        List<Movie> movies = movieCrawler.crawl(url);
        if (!movies.isEmpty()) moviesRepository.setMovies(movies);
    }

    public void sendMovieMail(String mail) {
        mailService.sendMovieMail(moviesRepository.getMovies(), mail);
    }

    public List<Movie> getMovieList() {
        return moviesRepository.getMovies();
    }

    //TODO: write validating logic
    private boolean validateMovie() {
        return true;
    }
}
