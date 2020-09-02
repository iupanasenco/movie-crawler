package com.iunona.crawler.service;

import com.iunona.crawler.model.Movie;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMovieMail(List<Movie> movies) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("some-mail@gmail.com");
        msg.setSubject("Movie list");

        String formattedMoviesList = formatMessage(movies);
        StringBuilder message = new StringBuilder("So this is the movie list from kinocrad.co for an awesome evening: \n");
        msg.setText(message.append(formattedMoviesList).toString());

        javaMailSender.send(msg);
    }

    private String formatMessage(List<Movie> movies) {
        return movies.stream().map(Movie::toString)
                .collect(Collectors.joining("\n"));

    }
}
