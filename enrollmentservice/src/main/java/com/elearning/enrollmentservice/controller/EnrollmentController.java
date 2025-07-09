package com.elearning.enrollmentservice.controller;

import com.elearning.enrollmentservice.entity.Enrollment;
import com.elearning.enrollmentservice.exception.CourseNotFoundException;
import com.elearning.enrollmentservice.exception.PaymentFailedException;
import com.elearning.enrollmentservice.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        try {
            Enrollment enrollment = enrollmentService.registerEnrollment(userId, courseId);
            return ResponseEntity.ok(enrollment);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentFailedException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}