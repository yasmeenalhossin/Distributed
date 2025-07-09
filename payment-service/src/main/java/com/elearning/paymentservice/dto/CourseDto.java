package com.elearning.paymentservice.dto;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
