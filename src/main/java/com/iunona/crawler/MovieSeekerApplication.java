package com.iunona.crawler;

import com.iunona.crawler.model.Movie;
import com.iunona.crawler.repo.MovieRepository;
import com.iunona.crawler.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieSeekerApplication {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    public static final String TEST_MAIL = "panasenco.iunona@gmail.com";
    public static final String BASE_URL = "https://kinokrad.co/";

    public static void main(String[] args) {

        //Test movie crawling and sending to mail
        ConfigurableApplicationContext context = SpringApplication.run(new Class<?>[]{MovieSeekerApplication.class}, args);
        context.getBean(MovieRepository.class).deleteAll();
        context.getBean(MovieService.class).crawlMovieList(BASE_URL);
        System.out.println(context.getBean(MovieRepository.class).findAll());

//        context.getBean(MovieService.class).sendMovieMail(TEST_MAIL);
    }

    @Bean
    public Supplier<String> getMovieList() {
        return ()->movieService.getMovieList().stream().map(Movie::toString)
                .collect(Collectors.joining("\n"));
    }
}
