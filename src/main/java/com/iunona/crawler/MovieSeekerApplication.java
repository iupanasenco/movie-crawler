package com.iunona.crawler;

import com.iunona.crawler.crawler.MovieCrawler;
import com.iunona.crawler.repo.MoviesRepository;
import com.iunona.crawler.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieSeekerApplication {

    public static void main(String[] args) {

        //Test movie crawling and sending to mail
        ConfigurableApplicationContext context = SpringApplication.run(new Class<?>[]{MovieSeekerApplication.class}, args);
        context.getBean(MovieCrawler.class).crawl("https://kinokrad.co/");
        System.out.println(context.getBean(MoviesRepository.class).getMovies());

        System.out.println("Sending Email...");
//        context.getBean(MovieService.class).sendMovieMail();
        System.out.println("Done");
    }
}
