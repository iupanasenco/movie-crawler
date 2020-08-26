package com.iunona.crawler.model;

import lombok.Data;

@Data
public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private Integer duration;
    private Integer rating;

    @Override
    public String toString() {
        return "* " + title +
                ", year:" + year +
                ", genre: " + genre +
                ", duration: " + duration +
                ", with rating: " + rating + ";\n";
    }
}
