package com.elearning.enrollmentservice.client;

import com.elearning.enrollmentservice.dto.PaymentRequest;
import com.elearning.enrollmentservice.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        return new PaymentResponse(false, "fallback-id", "Service unavailable");
    }
}