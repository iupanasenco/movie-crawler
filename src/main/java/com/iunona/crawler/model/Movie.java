package com.iunona.crawler.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.net.URL;
import java.util.Objects;

@Data
public class Movie {

    @Id
    public String id;


    private String title;
    private Integer year;
    private String genre;
    private Integer duration;
    private Integer rating;
    private URL url;

    @Override
    public String toString() {

        return String.format(
                "\n Movie[title: %s, year: '%d', genre:'%s', duration: '%d', with  rating: '%d'; \n '%s']",
                title, year, genre, duration, rating, url.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title) &&
                Objects.equals(year, movie.year) &&
                url.equals(movie.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, url);
    }
}
