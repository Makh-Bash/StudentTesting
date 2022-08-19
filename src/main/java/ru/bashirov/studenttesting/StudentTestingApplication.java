package ru.bashirov.studenttesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class StudentTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTestingApplication.class, args);
	}

//	TODO выяснить как работает
	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
