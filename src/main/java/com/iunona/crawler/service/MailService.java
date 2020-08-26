package com.iunona.crawler.service;

import com.iunona.crawler.model.Movie;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendTestEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("panasenco.iunona@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    //TODO: get rid of hardcoding
    public void sendMovieMail(List<Movie> movies) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("panasenco.iunona@gmail.com");
        msg.setSubject("My app spams you");

        String formattedMoviesList = formatMessage(movies);
        StringBuilder message = new StringBuilder("So this is the movie list from kinocrad.co for an awesome evening: \n");
        msg.setText(message.append(formattedMoviesList).toString());

        javaMailSender.send(msg);
    }

    private String formatMessage(List<Movie> movies) {
        String moviesFormatted = movies.stream().map(Movie::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(moviesFormatted);

        return moviesFormatted;

    }
}
