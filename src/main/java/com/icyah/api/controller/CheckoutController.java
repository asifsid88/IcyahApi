package com.icyah.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by asif on 12/30/16.
 */
@Controller
@Log4j2
public class CheckoutController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRazorPayButton(ModelMap modelMap) {
        log.info("Loading Razorpay checkout form");

        return "index";
    }

    @RequestMapping(value = "/checkout/init", method = RequestMethod.POST)
    public String captureRazorpayPayment(ModelMap modelMap) {
        log.info(modelMap);

        return "success";
    }
}
