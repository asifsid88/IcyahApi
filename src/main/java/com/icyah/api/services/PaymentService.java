package com.icyah.api.services;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asif on 12/31/16.
 */
@Service
@Log4j2
public class PaymentService {

    private RazorpayClient razorpayClient;

    @Autowired
    public PaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public List<Payment> fetchAllPayments() {
        List<Payment> paymentList = new ArrayList<Payment>();
        try {
            razorpayClient.Payments.fetchAll();
        } catch(RazorpayException re) {
            log.error("Error while fetching All payments: " + re);
        } catch(Exception e) {
            log.error("Generic Error in fetchAllPayments: " + e);
        }

        return paymentList;
    }

    public Payment getPayment(String paymentId) {
        Payment payment = null;
        try {
            razorpayClient.Payments.fetch(paymentId);
        } catch(RazorpayException re) {
            log.error("Error while getting payment with paymentId=" + paymentId + " : " + re);
        } catch(Exception e) {
            log.error("Generic error while getting payment with paymentId=" + paymentId + " : " + e);
        }

        return payment;
    }


    public boolean capturePayment(String paymentId, String amount) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", amount);
            razorpayClient.Payments.capture(paymentId, options);
            return true;
        } catch(RazorpayException re) {
            log.error("Error while capturePayment with paymentId=" + paymentId + " : " + re);
            return false;
        } catch(Exception e) {
            log.error("Generic error capturePayment with paymentId=" + paymentId + " : " + e);
            return false;
        }
    }
}
