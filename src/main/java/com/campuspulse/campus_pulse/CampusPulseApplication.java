package com.campuspulse.campus_pulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.campuspulse.campus_pulse")
@EnableMongoRepositories(basePackages = "com.campuspulse.campus_pulse.repository")
public class CampusPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusPulseApplication.class, args);
	}

}
