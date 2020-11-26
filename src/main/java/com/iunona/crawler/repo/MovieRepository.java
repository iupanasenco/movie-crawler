package com.iunona.crawler.repo;

import com.iunona.crawler.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
//    private List<Movie> movies = new ArrayList<>();
//
//    public void addMovie(Movie movie) {
//        movies.add(movie);
//    }




}
