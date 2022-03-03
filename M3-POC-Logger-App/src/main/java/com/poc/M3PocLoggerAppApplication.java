package com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class M3PocLoggerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(M3PocLoggerAppApplication.class, args);
		System.out.println("Logger Running");
	}

}
