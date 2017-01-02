package com.icyah.api.mail;

/**
 * Created by asif on 1/2/17.
 */

import com.icyah.api.mail.model.Email;
import com.icyah.api.mail.service.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServerDelegate {

    private final MailClient simpleMailService;

    @Autowired
    public MailServerDelegate(@Qualifier("simpleMailService") MailClient simpleMailService) {
        this.simpleMailService = simpleMailService;
    }

    public void sendMail(Email email) {
        simpleMailService.sendMail(email);
    }

    public void sendBulkMail(List<Email> emailList) {
        simpleMailService.sendBulkMail(emailList);
    }
}
