package com.placementtraining.jornalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		System.out.println("APPLICATION STARTING...");
		SpringApplication.run(JournalApplication.class, args);
	}
}
