package com.icyah.api.mail;

/**
 * Created by asif on 1/2/17.
 */

import com.icyah.api.mail.model.Email;
import org.springframework.mail.SimpleMailMessage;

import java.util.LinkedList;
import java.util.List;

public class MailMessage {

    public static SimpleMailMessage[] getMailMessageList(List<Email> emailList) {
        List<SimpleMailMessage> simpleMailMessageList = new LinkedList<SimpleMailMessage>();

        for(Email email : emailList) {
            simpleMailMessageList.add(getMailMessage(email));
        }

        SimpleMailMessage[] simpleMailMessages = new SimpleMailMessage[0];
        if(simpleMailMessageList.size() != 0) {
            simpleMailMessages = simpleMailMessageList.toArray(simpleMailMessages);
        }

        return simpleMailMessages;
    }

    public static SimpleMailMessage getMailMessage(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(email.getFromEmail());
        mailMessage.setTo(email.getToEmail().split(";"));
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());

        return mailMessage;
    }
}