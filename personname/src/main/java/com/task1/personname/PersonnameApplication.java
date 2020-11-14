package com.task1.personname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.task1.personname.*")
public class PersonnameApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PersonnameApplication.class, args);
	}

}
