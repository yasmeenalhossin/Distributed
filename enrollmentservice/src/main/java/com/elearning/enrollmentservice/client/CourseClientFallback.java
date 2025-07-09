package com.elearning.enrollmentservice.client;

import com.elearning.enrollmentservice.dto.CourseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseClientFallback implements CourseClient {
    @Override
    public Boolean checkCourseExists(Long courseId) {
        return false;
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return null;
    }

    @Override
    public Double getCoursePrice(Long courseId) {
        return 0.0;
    }
}