package com.bhel.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BhelApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/bhel");
		SpringApplication.run(BhelApplication.class, args);
	}

}
