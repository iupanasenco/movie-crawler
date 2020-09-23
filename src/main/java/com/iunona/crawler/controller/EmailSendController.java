package com.iunona.crawler.controller;

import com.iunona.crawler.dto.MailSendErrorDTO;
import com.iunona.crawler.model.Movie;
import com.iunona.crawler.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmailSendController {

    private final MovieService movieService;

    @GetMapping("/send-movie-mail")
    public ResponseEntity<Object> sendMail(@RequestParam String mail) {
        try {
            movieService.sendMovieMail(mail);
        } catch (MailSendException e) {
            return new ResponseEntity<>(new MailSendErrorDTO("Mail not sent!", mail, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }

}
