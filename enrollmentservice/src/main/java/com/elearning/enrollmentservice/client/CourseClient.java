package com.elearning.enrollmentservice.client;

import com.elearning.enrollmentservice.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", fallback = CourseClientFallback.class)
public interface CourseClient {
    @GetMapping("/api/courses/{id}/exists")
    Boolean checkCourseExists(@PathVariable("id") Long courseId);

    @GetMapping("/api/courses/{id}")
    CourseDto getCourseById(@PathVariable("id") Long courseId);

    @GetMapping("/api/courses/{id}/price")
    Double getCoursePrice(@PathVariable("id") Long courseId);
}