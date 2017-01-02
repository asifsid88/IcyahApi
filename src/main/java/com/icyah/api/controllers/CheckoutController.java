package com.icyah.api.controllers;

import com.icyah.api.CreateMail;
import com.icyah.api.mail.MailServerDelegate;
import com.icyah.api.services.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by asif on 12/30/16.
 */
@Controller
@Log4j2
public class CheckoutController {

    @Value("#{systemProperties['key']}")
    private String key;

    @Value("#{systemProperties['secret']}")
    private String secret;


    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MailServerDelegate mailServer;

    private static final String paymentIdParameter = "razorpay_payment_id";
    private static final String conferenceTicketCost = "50000"; // INR 500

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRazorPayButton(ModelMap modelMap) {
        log.error("key=" + key + ", secret=" + secret);
        System.out.println("key=" + key + ", secret=" + secret);
        log.info("Loading Razorpay checkout form");

        return "index";
    }

    @RequestMapping(value = "/checkout/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String captureRazorpayPayment(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] paymentIds = parameterMap.get(paymentIdParameter);
        if(paymentIds == null || paymentIds.length < 1) {
            log.error("PaymentId null found in response from razorpay");
            return "error";
        }

        // get the razor_payment_id and capture the transaction
        boolean capturePaymentStatus = paymentService.capturePayment(paymentIds[0], conferenceTicketCost);

        if(capturePaymentStatus) {
            log.info("Successfully captured the payment");

            // send the mail to both
            log.info("Sending mail");
            mailServer.sendMail(CreateMail.getMailTemplate("asif.sid88@gmail.com"));
            log.info("Mail sent successfully");

            return "success";
        } else {
            log.error("Error in capturing payment with paymentId=" + paymentIds[0]);
            return "error";
        }
    }
}
