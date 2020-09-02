package com.iunona.crawler.service;

import com.iunona.crawler.model.Movie;
import com.iunona.crawler.repo.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private final MoviesRepository moviesRepository;
    private final MailService mailService;

    public void addMovie(Movie movie) {
        if (validateMovie())
            moviesRepository.addMovie(movie);
    }

    public void sendMovieMail() {
        mailService.sendMovieMail(moviesRepository.getMovies());
    }

    public List<Movie> getMovieList() {
        return moviesRepository.getMovies();
    }

    //TODO: write validating logic
    private boolean validateMovie() {
        return true;
    }
}
