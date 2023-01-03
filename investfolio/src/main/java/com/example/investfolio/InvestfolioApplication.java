package com.example.investfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableScheduling
public class InvestfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestfolioApplication.class, args);
	}




}
