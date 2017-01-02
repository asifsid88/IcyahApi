package com.icyah.api.mail.types;

/**
 * Created by asif on 1/2/17.
 */

import com.icyah.api.mail.MailMessage;
import com.icyah.api.mail.model.Email;
import com.icyah.api.mail.service.MailClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simpleMailService")
@Log4j2
public class SimpleMailService implements MailClient {

    private final MailSender mailSender;

    @Autowired
    public SimpleMailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send single mail
     * @param email Mail object to send
     */
    public void sendMail(Email email) {
        try {
            mailSender.send(MailMessage.getMailMessage(email));
            log.info("1 mail sent");
        } catch(Exception e) {
            log.error("Exception occurred while sending single mail: Exception: ", e);
        }
    }

    /**
     * Send bulk mails
     * @param emailList : List of Mails to sent
     */
    public void sendBulkMail(List<Email> emailList) {
        try {
            mailSender.send(MailMessage.getMailMessageList(emailList));
            log.info("{} mails sent", emailList.size());
        } catch(Exception e) {
            log.error("Exception occurred while sending {} mails: Exception: ", emailList.size(), e);
        }
    }
}