package com.icyah.api.mail.model;

/**
 * Created by asif on 1/2/17.
 */

import lombok.Data;

@Data
public class Email {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
}
