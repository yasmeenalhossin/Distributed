package com.elearning.enrollmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EnrollmentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EnrollmentServiceApplication.class, args);
	}
}
