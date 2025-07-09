package com.elearning.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long courseId;
    private Long orderId; 
    private Double amount;
    private String status;
    private String transactionId; 

    private LocalDateTime paymentDate;
}
