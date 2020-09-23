package com.iunona.crawler.service;

import com.iunona.crawler.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMovieMail(List<Movie> movies, String mail) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(mail);
            msg.setSubject("Movie list");

            String formattedMoviesList = formatMessage(movies);
            StringBuilder message = new StringBuilder("Here is the movie list from kinocrad.co for an awesome evening: \n");
            msg.setText(message.append(formattedMoviesList).toString());

            javaMailSender.send(msg);
            log.info("Movie message sent to " + mail + ";");
        } catch (MailException mailException) {
            log.error(mailException.getMessage());
            throw new MailSendException(Objects.requireNonNull(mailException.getMessage()));
        }
    }

    private String formatMessage(List<Movie> movies) {
        return movies.stream().map(Movie::toString)
                .collect(Collectors.joining("\n"));
    }
}
