package com.example.smartpantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SmartpantryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartpantryApplication.class, args);
	}

}
