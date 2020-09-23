package com.iunona.crawler.model;

import lombok.Data;

import java.net.URL;

@Data
public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private Integer duration;
    private Integer rating;
    private URL url;

    @Override
    public String toString() {
        return "* " + title +
                ", year:" + year +
                ", genre: " + genre +
                ", duration: " + duration +
                ", with rating: " + rating + ";\n" +
                url;
    }
}
