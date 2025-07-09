package com.elearning.enrollmentservice.client;

import com.elearning.enrollmentservice.dto.PaymentRequest;
import com.elearning.enrollmentservice.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @PostMapping("/api/payments")
    PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest);
}