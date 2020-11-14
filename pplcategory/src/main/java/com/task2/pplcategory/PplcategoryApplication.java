package com.task2.pplcategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.task2.pplcategory.*")
@EnableCaching
@EnableScheduling
public class PplcategoryApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PplcategoryApplication.class, args);
	}

}
