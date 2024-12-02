package com.example.smartpantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.example.smartpantry")
public class SmartpantryApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartpantryApplication.class, args);
	}
}

