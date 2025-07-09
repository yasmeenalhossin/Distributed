package com.elearning.paymentservice.controller;

import com.elearning.paymentservice.entity.Payment;
import com.elearning.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    
    @PostMapping
    public Payment doPayment(@RequestBody Payment payment) {
        return paymentService.doPayment(payment);
    }

    @PostMapping("/manual")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

   
    @GetMapping("/by-id/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

   
    @GetMapping("/by-order/{orderId}")
    public Payment getPaymentDetails(@PathVariable Long orderId) {
        return paymentService.getPaymentDetailsByOrderId(orderId);
    }
}
