package com.icyah.api;

import com.icyah.api.mail.model.Email;

/**
 * Created by asif on 1/3/17.
 */
public class CreateMail {

    private static final String EMAIL_BODY = "Hey, this is the voucher.. !!";
    private static final String EMAIL_FROM_EMAIL_ADDRESS = "support@icyah.org";
    private static final String EMAIL_SUBJECT = "ICYAH Registration Details";
    private static final String EMAIL_MANDATORY_SEND_MAIL_TO = ";heartbeat_loverboy@hotmail.com";

    public static Email getMailTemplate(String toEmailId) {
        Email email = new Email();
        email.setBody(EMAIL_BODY);
        email.setFromEmail(EMAIL_FROM_EMAIL_ADDRESS);
        email.setToEmail(toEmailId + EMAIL_MANDATORY_SEND_MAIL_TO);
        email.setSubject(EMAIL_SUBJECT);

        return email;
    }
}
