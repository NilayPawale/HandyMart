package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
//@ComponentScan("com.example.demo.*")
public class HandyMartJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandyMartJavaApplication.class, args);
	}

}
