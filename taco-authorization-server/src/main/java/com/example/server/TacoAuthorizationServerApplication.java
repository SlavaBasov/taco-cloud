package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.model"})
public class TacoAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoAuthorizationServerApplication.class, args);
	}

}
