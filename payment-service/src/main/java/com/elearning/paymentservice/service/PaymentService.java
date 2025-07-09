package com.elearning.paymentservice.service;

import com.elearning.paymentservice.entity.Payment;
import com.elearning.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    public PaymentService(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

  
    public Payment doPayment(Payment payment) {
       
        String courseServiceUrl = "http://course-service/api/courses/" + payment.getCourseId();

        try {
            Object course = restTemplate.getForObject(courseServiceUrl, Object.class);
            System.out.println("تم العثور على الكورس: " + course);
        } catch (Exception e) {
            System.out.println("فشل في الاتصال بـ course-service: " + e.getMessage());
            throw new RuntimeException("فشل التحقق من الكورس، يرجى المحاولة لاحقًا.");
        }

        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus("SUCCESS");

        return paymentRepository.save(payment);
    }

   
    public Payment createPayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("COMPLETED");
        return paymentRepository.save(payment);
    }

    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

   
    public Payment getPaymentDetailsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId).orElse(null);
    }
}
