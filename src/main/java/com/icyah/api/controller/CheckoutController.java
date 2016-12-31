package com.icyah.api.controller;

import lombok.extern.log4j.Log4j2;
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

    // Add the services

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRazorPayButton(ModelMap modelMap) {
        log.info("Loading Razorpay checkout form");

        return "index";
    }

    @RequestMapping(value = "/checkout/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String captureRazorpayPayment(HttpServletRequest request) {
        Map<String, String> parameterMap = request.getParameterMap();
        System.out.println("Data received: " + parameterMap);

        // get the razor_payment_id and capture the transaction

        // upon successful capturing.. store the transaction in our db for reference

        // Also sent a mail to the person you have made the purchase and also icyah mailing group

        // Redirect the call to front-end to show ThankYou page

        return "success";
    }

    /**
     * TODO: Add refund mechanism
     */
}
