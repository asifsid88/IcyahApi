package com.icyah.api.mail.service;

/**
 * Created by asif on 1/2/17.
 */

import com.icyah.api.mail.model.Email;

import java.util.List;

public interface MailClient {
    void sendMail(Email email);
    void sendBulkMail(List<Email> emailList);
}
