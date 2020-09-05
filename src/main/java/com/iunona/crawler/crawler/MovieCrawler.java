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
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovieCrawler {

    private static final String MOVIES_ELEMENTS_DIV = "div.shorbox";
    public static final String TITLE = "h2 a";
    public static final String RATING_HTML = "div.rating li.current-rating";
    public static final String MOVIE_INFO_HTML = "span.orange";

    public List<Movie> crawl(String url) {

        List<Movie> movieList = new ArrayList<>();
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

                movieList.add(movie);
                log.info("Crawled a movie: " + movie.getTitle());
            }
        } catch (IOException e) {
            log.error("Not today");
        }

        return movieList;
    }
}
