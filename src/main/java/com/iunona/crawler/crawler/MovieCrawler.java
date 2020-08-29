package com.iunona.crawler.crawler;

import com.iunona.crawler.model.Movie;
import com.iunona.crawler.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
@Slf4j
public class MovieCrawler {

    private final MovieService movieService;

    private static final String MOVIES_ELEMENTS_DIV = "div.shorbox";
    public static final String TITLE = "h2 a";
    public static final String RATING_HTML = "div.rating li.current-rating";
    public static final String MOVIE_INFO_HTML = "span.orange";

    //TODO: add functionality
    public MovieCrawler(MovieService movieService) {
        this.movieService = movieService;
    }

    public void crawl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements moviesElements = doc.select(MOVIES_ELEMENTS_DIV);

            for (Element e :
                    moviesElements) {
                Movie movie = new Movie();
                movie.setTitle(e.select(TITLE).text());
                movie.setUrl(new URL(e.select(TITLE).attr("href")));

                movie.setRating(Integer.parseInt(e.select(RATING_HTML).text()));
                Elements movieInfo = e.select(MOVIE_INFO_HTML);

                movie.setGenre(movieInfo.get(2).text());

                String duration = movieInfo.get(5).text().replaceAll("[^0-9]", "");
                movie.setDuration(Integer.parseInt(duration));

                movie.setYear(Integer.parseInt(movieInfo.get(0).text()));

                movieService.addMovie(movie);
                log.info("Crawled a movie: " + movie.getTitle());
            }
        } catch (IOException e) {
            log.error("Not today");
        }
    }
}
