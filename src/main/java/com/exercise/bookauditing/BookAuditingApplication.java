package com.exercise.bookauditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class BookAuditingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAuditingApplication.class, args);
	}

}
