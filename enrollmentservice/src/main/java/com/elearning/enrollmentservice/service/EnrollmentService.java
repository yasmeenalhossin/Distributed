package com.elearning.enrollmentservice.service;

import com.elearning.enrollmentservice.client.CourseClient;
import com.elearning.enrollmentservice.client.PaymentClient;
import com.elearning.enrollmentservice.dto.PaymentRequest;
import com.elearning.enrollmentservice.dto.PaymentResponse;
import com.elearning.enrollmentservice.entity.Enrollment;
import com.elearning.enrollmentservice.entity.EnrollmentStatus;
import com.elearning.enrollmentservice.exception.CourseNotFoundException;
import com.elearning.enrollmentservice.exception.PaymentFailedException;
import com.elearning.enrollmentservice.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseClient courseClient;
    private final PaymentClient paymentClient;

    @Transactional
    public Enrollment registerEnrollment(Long userId, Long courseId) {
        Boolean courseExists = courseClient.checkCourseExists(courseId);
        if (courseExists == null || !courseExists) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found");
        }

        Double coursePrice = courseClient.getCoursePrice(courseId);

        // إنشاء التسجيل
        Enrollment enrollment = Enrollment.builder()
                .userId(userId)
                .courseId(courseId)
                .enrollmentDate(LocalDateTime.now())
                .status(EnrollmentStatus.REGISTERED)
                .build();

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        PaymentRequest paymentRequest = new PaymentRequest(
                userId,
                courseId,
                savedEnrollment.getId(),
                coursePrice,
                "CREDIT_CARD");

        PaymentResponse paymentResponse = paymentClient.createPayment(paymentRequest);

        if (paymentResponse == null || !paymentResponse.isSuccess()) {
            throw new PaymentFailedException("Payment failed for enrollment: " + savedEnrollment.getId());
        }

        log.info("Payment successful for enrollment: {}, transaction ID: {}",
                savedEnrollment.getId(),
                paymentResponse.getTransactionId());

        return savedEnrollment;
    }
}