package com.iunona.crawler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailSendErrorDTO {

    private String email;
    private String message;
    private String cause;

}
