package com.icyah.api;

import com.razorpay.RazorpayClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by asif on 12/31/16.
 */
@Component
@Log4j2
public class BeanFactory {

    @Bean
    public static RazorpayClient getRazorpayClient() {
        return new RazorpayClient("rzp_test_mUK32sgx627JnY", "toacnYRT3tqebXNyCowCpVx5");
        // return new RazorpayClient(System.getProperty("key"), System.getProperty("secret"));
    }

    @Bean
    public static JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        /*
        Which hosts is used to sent mail from
         */
        javaMailSender.setHost(System.getProperty(PropertyName.MAIL_HOST, PropertyDefaultValue.MAIL_HOST));

        /*
        Port number
         */
        Integer port = Integer.parseInt(System.getProperty(PropertyName.MAIL_PORT, PropertyDefaultValue.MAIL_PORT));
        javaMailSender.setPort(port);

        /*
        The user name and password used to authenticate to sent mails
         */
        javaMailSender.setUsername(System.getProperty(PropertyName.MAIL_USERNAME, PropertyDefaultValue.MAIL_USERNAME));
        javaMailSender.setPassword(System.getProperty(PropertyName.MAIL_PASSWORD, PropertyDefaultValue.MAIL_PASSWORD));

        /*
        Properties/Configuration the mail server needs
         */
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private static Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyName.MAIL_TRANSPORT_PROTOCOL, System.getProperty(PropertyName.MAIL_TRANSPORT_PROTOCOL, PropertyDefaultValue.MAIL_TRANSPORT_PROTOCOL));
        properties.setProperty(PropertyName.MAIL_SMTP_AUTH, System.getProperty(PropertyName.MAIL_SMTP_AUTH, PropertyDefaultValue.MAIL_SMTP_AUTH));
        properties.setProperty(PropertyName.MAIL_SMTP_STARTTLS_ENABLE, System.getProperty(PropertyName.MAIL_SMTP_STARTTLS_ENABLE, PropertyDefaultValue.MAIL_SMTP_STARTTLS_ENABLE));

        /*
        Keeping Debug=true will help to see the debug logs (used to sent mail)
         */
        properties.setProperty(PropertyName.MAIL_DEBUG, System.getProperty(PropertyName.MAIL_DEBUG, PropertyDefaultValue.MAIL_DEBUG));

        return properties;
    }


    @PostConstruct
    public void logEnvironment() {
        log.debug("Spring Configuration loaded !!");
    }
}
